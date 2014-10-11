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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ngo.interfaces.event.EventService;
import com.ngo.interfaces.user.UserService;
import com.ngo.model.Event;
import com.ngo.model.User;

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
	@Path("/add/{event}")
	public int addEvent(@PathParam("event") EventForm event){
		return eventService.addEvent(event);
		
	}
	
	@PUT
	@Path("/update/{event}")
	public void updateEvent(@PathParam("event") EventForm event){
		 eventService.updateEvent(event);
		
	}
	
	@DELETE
	@Path("/delete/{id}")
	public void deleteEvent(@PathParam("id") int id){
		 eventService.deleteEvent(id);
		
	}
	
	

}
