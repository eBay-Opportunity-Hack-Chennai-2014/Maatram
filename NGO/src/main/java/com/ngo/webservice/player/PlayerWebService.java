/*	Heroes Persist
    Product which helps in organizing, broadcasting, celebrating events
    Copyright (C) 2014  Sai Pranav
    Email: rsaipranav92@gmail.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.ngo.webservice.player;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ngo.dto.PlayerWrapper;
import com.ngo.exception.RESTException;
import com.ngo.interfaces.game.GameService;
import com.ngo.interfaces.player.PlayerService;
import com.ngo.model.Game;
import com.ngo.utility.DTOUtility;
import com.ngo.webservice.game.GameFormWithoutNumberOfPlayers;

/**
 * @author Sai Pranav
 *
 */
@Component
@Path("/players")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerWebService {

	@Autowired
	PlayerService playerService;
	
	@Autowired
	GameService gameService;
	
	@POST
	public Response addPlayer(PlayerForm playerForm) {
		int tempId;
		try{
			tempId = playerService.addPlayer(playerForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@PUT
	@Path("/{employeeId}")
	public Response modifyPlayer(@PathParam("employeeId") int employeeId, PlayerForm playerForm) {
		int tempId;
		try{
			tempId = playerService.modifyPlayer(employeeId, playerForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@DELETE
	@Path("/{employeeId}")
	public Response deletePlayer(@PathParam("employeeId") int employeeId) {
		int tempId;
		try{
			tempId = playerService.deletePlayer(employeeId);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@POST
	@Path("/{employeeId}/games")
	public Response modifyPlayerAddGame(@PathParam("employeeId") int employeeId, GameFormWithoutNumberOfPlayers gameFormWithoutNumberOfPlayers) {
		int tempId;
		try{
			tempId = playerService.modifyPlayerAddGame(employeeId, gameFormWithoutNumberOfPlayers);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@DELETE
	@Path("/{employeeId}/games")
	public Response modifyPlayerDeleteGame(@PathParam("employeeId") int employeeId, GameFormWithoutNumberOfPlayers gameFormWithoutNumberOfPlayers) {
		int tempId;
		try{
			tempId = playerService.modifyPlayerDeleteGame(employeeId, gameFormWithoutNumberOfPlayers);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@POST
	@Path("/admin/{id}")
	public Response modifyPlayerFromAdmin(@PathParam("employeeId") int employeeId, PlayerForm playerForm) {
		int tempId;
		try{
			tempId = playerService.modifyPlayerFromAdmin(employeeId, playerForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@GET
	public PlayerWrapper getPlayersList() {
		return DTOUtility.createPlayerWrapper(playerService.getPlayers());
	}
	
	@GET
	@Path("/{playerId}/games")
	public List<Game> getGamesByPlayer(@PathParam("playerId") int employeeId) {
		try{
			return gameService.getGamesByPlayer(employeeId);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
	}
	
}
