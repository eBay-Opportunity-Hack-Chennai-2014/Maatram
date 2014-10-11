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
package com.ngo.webservice.match;

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
import com.ngo.dto.TeamWrapper;
import com.ngo.exception.RESTException;
import com.ngo.interfaces.match.MatchService;
import com.ngo.interfaces.team.TeamService;
import com.ngo.model.Match;
import com.ngo.utility.DTOUtility;

/**
 * @author Sai Pranav
 *
 */
@Component
@Path("/matches")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatchWebService {

	@Autowired
	MatchService matchService;
	
	@Autowired
	TeamService teamService;
	
	@POST
	public Response addMatch(MatchForm matchForm) {
		int tempId;
		try{
			tempId = matchService.addMatch(matchForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@PUT
	@Path("/{id}")
	public Response modifyMatch(@PathParam("id") int id, MatchForm matchForm){
		int tempId;
		try{
			tempId = matchService.modifyMatch(id, matchForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteMatch(@PathParam("id") int id){
		int tempId;
		try{
			tempId = matchService.deleteMatch(id);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@POST
	@Path("/{id}/teams/{teamName}")
	public Response modifyMatchAddTeams(@PathParam("id") int id, @PathParam("teamName") String teamName) {
		int tempId;
		try{
			tempId = matchService.modifyMatchAddTeams(id, teamName);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@DELETE
	@Path("/{id}/teams/{teamName}")
	public Response modifyMatchDeleteTeams(@PathParam("id") int id, @PathParam("teamName") String teamName) {
		int tempId;
		try{
			tempId = matchService.modifyMatchDeleteTeams(id, teamName);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@PUT
	@Path("/{id}/show/{toShow}")
	public Response modifyMatchModifyShow(@PathParam("id") int id, @PathParam("toShow") boolean toShow) {
		int tempId;
		try{
			tempId = matchService.modifyMatchmodifyShow(id, toShow);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@PUT
	@Path("/admin/{id}")
	public Response modifyMatchFromAdmin(@PathParam("id") int id, MatchForm matchForm) {
		int tempId;
		try{
			tempId = matchService.modifyMatchFromAdmin(id, matchForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@PUT
	@Path("/livescores/{id}")
	public Response modifyMatchFromLivescores(@PathParam("id") int id, SimpleMatchForm simpleMatchForm) {
		int tempId;
		try{
			tempId = matchService.modifyMatchFromLivescores(id, simpleMatchForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@GET
	public MatchWrapper allMatches(@QueryParam("showCriteria") boolean showCriteria) {
		return DTOUtility.createMatchWrapper(matchService.allMatches(showCriteria));
	}
	
	@GET
	@Path("/{id}/playeremails")
	public List<String> playerEmails(@PathParam("id") int id) {
		try {
			return matchService.playerEmails(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/{id}/teams")
	public TeamWrapper getTeamsByMatch(@PathParam("id") int id) {
		try{
			return DTOUtility.createTeamWrapper(teamService.getTeamsByMatch(id));
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
	}
	
	@GET
	@Path("/{id}")
	public Match getMatch(@PathParam("id") int id) {
		try{
			return matchService.getMatch(id);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
	}
	
}
