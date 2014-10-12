package com.ngo.interfaces.event;

import java.util.List;

import com.ngo.model.Event;

public interface EventDao {
	
	public int addEvent(Event event);
	public int updateEvent(Event event);
	public int deleteEvent(Event id);
	public List<Event> getEvents(String month,String year);
	
}
