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
package com.ngo.webservice.game;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ngo.dto.MatchWrapper;
import com.ngo.dto.PlayerWrapper;
import com.ngo.dto.TeamWrapper;
import com.ngo.exception.RESTException;
import com.ngo.interfaces.game.GameService;
import com.ngo.interfaces.match.MatchService;
import com.ngo.interfaces.player.PlayerService;
import com.ngo.interfaces.team.TeamService;
import com.ngo.model.Game;
import com.ngo.utility.DTOUtility;

/**
 * @author Sai Pranav
 *
 */
@Component
@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameWebService {

	@Autowired
	GameService gameService;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	MatchService matchService;
	
	@POST
	public Response addGame(GameForm gameForm) {
		int tempId;
		try{
			tempId = gameService.addGame(gameForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@PUT
	@Path("/{id}")
	public Response modifyGame(@PathParam("id") int id, GameForm gameForm) {
		int tempId;
		try{
			tempId = gameService.modifyGame(id, gameForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteGame(@PathParam("id") int id) {
		int tempId;
		try{
			tempId = gameService.deleteGame(id);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@GET
	public List<Game> getGameList() {
		return gameService.getGames();
	}
	
	@GET
	@Path("/players")
	public PlayerWrapper getPlayersByGame(@QueryParam("gameName") String gameName, @QueryParam("gameCategory") String gameCategory) {
		try{
			return DTOUtility.createPlayerWrapper(playerService.getPlayersByGame(gameName, gameCategory));
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
	}
	
	@GET
	@Path("/teams")
	public TeamWrapper getTeamsByGame(@QueryParam("gameName") String gameName, @QueryParam("gameCategory") String gameCategory, @QueryParam("showCriteria") boolean showCriteria) {
		try{
			return DTOUtility.createTeamWrapper(teamService.getTeamsByGame(gameName, gameCategory,showCriteria));
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
	}
	
	@GET
	@Path("/matches")
	public MatchWrapper getMatchesByGame(@QueryParam("gameName") String gameName, @QueryParam("gameCategory") String gameCategory) {
		try{
			return DTOUtility.createMatchWrapper(matchService.getMatchesByGame(gameName, gameCategory));
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
	}
	
}
