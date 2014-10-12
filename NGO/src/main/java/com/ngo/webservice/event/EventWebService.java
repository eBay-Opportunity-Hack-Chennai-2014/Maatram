package com.ngo.webservice.event;

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

import com.ngo.exception.RESTException;
import com.ngo.interfaces.event.EventService;
import com.ngo.model.Event;

@Component
@Path("/event")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventWebService {

	@Autowired
	EventService eventService;
	
	@GET
	public List<Event> listEvents(@QueryParam("month") String month, @QueryParam("year") String year) {
		return eventService.getEvents(month,year);
	}
	
	@GET
	@Path("/{id}")
	public Event getEvent(@PathParam("id") int id){
		return eventService.getEvent(id);
	}
	
	@POST
	public Response addUser(EventForm event) {
		int tempId;
		try{
			tempId = eventService.addEvent(event);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@PUT
	@Path("/{id}")
	public Response updateEvent(@PathParam("id") int id, EventForm event) {
		int tempId;
		try{
			tempId = eventService.updateEvent(id,event);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteEvent(@PathParam("id") int id) {
		int tempId;
		try{
			tempId = eventService.deleteEvent(id);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	

}

