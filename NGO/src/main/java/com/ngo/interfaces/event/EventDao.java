package com.ngo.interfaces.event;

import com.ngo.model.Event;

public interface EventDao {
	
	public int addEvent(Event event);
	public void updateEvent(Event event);
	public void deleteEvent(int id);
	
}
