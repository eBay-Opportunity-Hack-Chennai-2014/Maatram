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
package com.ngo.webservice.team;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ngo.dto.PlayerWrapper;
import com.ngo.exception.RESTException;
import com.ngo.exception.SportsException;
import com.ngo.interfaces.player.PlayerService;
import com.ngo.interfaces.team.TeamService;
import com.ngo.model.Team;
import com.ngo.utility.DTOUtility;

/**
 * @author Sai Pranav
 *
 */
@Component
@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamWebService {

	@Autowired
	TeamService teamService;
	
	@Autowired
	PlayerService playerService;
	
	@Value( "${registrationOpen:false}" )
	private String registrationOpen;
	
	@POST
	public Response addTeam(@Context HttpServletRequest req, TeamForm teamForm) {
		int tempId;
		try{
			if(registrationOpen.equals("true")){
				if(teamForm.getWhoAmI().equals("user")){
					tempId = teamService.addTeam(req.getRemoteAddr(), teamForm);
				}
				else{
					tempId = teamService.addTeam("", teamForm);
				}
			}
			else{
				throw new SportsException("Registrations are not yet OPEN!");
			}
/*			teamForm.getPlayers().remove(0);*/
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@PUT
	@Path("/{id}")
	public Response modifyTeam(@PathParam("id") int id, TeamForm teamForm){
		int tempId;
		try{
			tempId = teamService.modifyTeam(id,teamForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@PUT
	@Path("/livescores/{id}")
	public Response modifyTeamFromLivescores(@PathParam("id") int id, SimpleTeamForm teamFormSimple){
		int tempId;
		try{
			tempId = teamService.modifyTeam(id,teamFormSimple);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteTeam(@PathParam("id") int id){
		int tempId;
		try{
			tempId = teamService.deleteTeam(id);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@POST
	@Path("/{id}/players/{employeeId}")
	public Response modifyTeamAddPlayer(@PathParam("id") int id, @PathParam("employeeId") String employeeId) {
		int tempId;
		try{
			tempId = teamService.modifyTeamAddPlayer(id, employeeId);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@DELETE
	@Path("/{id}/players/{employeeId}")
	public Response modifyTeamDeletePlayer(@PathParam("id") int id, @PathParam("employeeId") String employeeId) {
		int tempId;
		try{
			tempId = teamService.modifyTeamDeletePlayer(id, employeeId);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@PUT
	@Path("/{id}/show/{toShow}")
	public Response modifyTeamShow(@PathParam("id") int id, @PathParam("toShow") boolean toShow) {
		int tempId;
		try{
			tempId = teamService.modifyTeamShow(id, toShow);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@PUT
	@Path("/admin/{id}")
	public Response modifyTeamFromAdmin(@PathParam("id") int id, TeamForm teamForm) {
		int tempId;
		try{
			/*teamForm.getPlayers().remove(0);*/
			tempId = teamService.modifyTeamFromAdmin(id, teamForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@GET
	@Path("{teamid}/players")
	public PlayerWrapper getPlayersByTeam(@PathParam("teamid") int teamId) {
		try{
			return DTOUtility.createPlayerWrapper(playerService.getPlayersByTeam(teamId));
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
	}
	
	@GET
	@Path("{teamid}/playeremails")
	public List<String> getPlayersEmailsByTeam(@PathParam("teamid") int teamId) {
		try{
			return playerService.getPlayerEmails(teamId);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
	}
	
	@GET
	@Path("{teamid}")
	public Team getTeam(@PathParam("teamid") int teamId) {
		try{
			return teamService.getTeam(teamId);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
	}
	
}
