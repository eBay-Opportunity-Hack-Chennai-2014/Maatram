package com.ngo.interfaces.event;

import java.util.List;

import com.ngo.model.Event;
import com.ngo.model.User;
import com.ngo.webservice.event.EventForm;

public interface EventService {

	//Core User functions
	public int addEvent(EventForm event);
	public int updateEvent(int id,EventForm eventForm);
	public int deleteEvent(int id);
	
	//Utility functions
	public Event getEvent(int id);
	public List<Event> getEvents(String month, String year);
}
