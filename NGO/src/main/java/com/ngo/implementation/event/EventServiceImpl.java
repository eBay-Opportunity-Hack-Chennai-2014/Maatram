package com.ngo.implementation.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ngo.exception.NGOException;
import com.ngo.interfaces.event.EventDao;
import com.ngo.interfaces.event.EventService;
import com.ngo.interfaces.user.UserDao;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Event;
import com.ngo.model.User;
import com.ngo.webservice.event.EventForm;
import com.ngo.webservice.user.UserFormForChecking;


public class EventServiceImpl implements EventService {

	@Autowired
	EventDao eventDao;

	@Autowired
	UtilityDao utilityDao;

	@Override
	public int addEvent(EventForm eventF) {

		Event event = utilityDao.getEvent(eventF.getId());
		if (event != null)
			throw new NGOException("Event already exists");

		event = new Event();
		event.setCategory(eventF.getCategory());
		event.setDate(eventF.getDate());
		event.setId(eventF.getId());
		event.setLocation(eventF.getLocation());
		event.setResourceAcquired(eventF.getResourceAcquired());
		event.setResourceNeeded(eventF.getResourceNeeded());

		return eventDao.addEvent(event);
	}

	@Override
	public void updateEvent(EventForm eventForm) {
		Event event = utilityDao.getEvent(eventForm.getId());
		if (event == null)
			throw new NGOException("Event does n't exist!");
		event.setCategory(eventForm.getCategory());
		event.setDate(eventForm.getDate());
		event.setId(eventForm.getId());
		event.setLocation(eventForm.getLocation());
		event.setResourceAcquired(eventForm.getResourceAcquired());
		event.setResourceNeeded(eventForm.getResourceNeeded());
		
		eventDao.updateEvent(event);

	}

	@Override
	public void deleteEvent(int id) {
		Event event = utilityDao.getEvent(id);
		if (event == null)
			throw new NGOException("Event does n't exist!");
		eventDao.deleteEvent(id);
	}

	

		@Override
	public Event getEvent(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getEvents(String month, String year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getEvents(int org_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
