package com.ngo.implementation.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ngo.exception.NGOException;
import com.ngo.interfaces.event.EventDao;
import com.ngo.interfaces.event.EventService;
import com.ngo.interfaces.user.UserDao;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Event;
import com.ngo.model.User;
import com.ngo.webservice.event.EventForm;
import com.ngo.webservice.user.UserFormForChecking;

@Component
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	EventDao eventDao;

	@Autowired
	UtilityDao utilityDao;

	@Override
	public int addEvent(EventForm eventF) {
		
		

		Event event = new Event();
		
		event.setTitle(eventF.getTitle());
		event.setDescription(eventF.getDescription());
		event.setCategory(eventF.getCategory());
		event.setDate(eventF.getDate());
		event.setLocation(eventF.getLocation());
		event.setResourceAcquired(eventF.getResourceAcquired());
		event.setResourceNeeded(eventF.getResourceNeeded());
		event.setYear(eventF.getYear());
		event.setMonth(eventF.getMonth());
		event.setOrg_id(eventF.getOrg_id());

		return eventDao.addEvent(event);
	}

	@Override
	public int updateEvent(int id, EventForm eventForm) {
		Event event = utilityDao.getEvent(id);
		if (event == null)
			throw new NGOException("Event does n't exist!");
		event.setTitle(eventForm.getTitle());
		event.setDescription(eventForm.getDescription());
		event.setCategory(eventForm.getCategory());
		event.setDate(eventForm.getDate());
		event.setLocation(eventForm.getLocation());
		event.setResourceAcquired(eventForm.getResourceAcquired());
		event.setResourceNeeded(eventForm.getResourceNeeded());
		event.setYear(eventForm.getYear());
		event.setMonth(eventForm.getMonth());
		event.setOrg_id(eventForm.getOrg_id());
		
		return eventDao.updateEvent(event);

	}

	@Override
	public int deleteEvent(int id) {
		Event event = utilityDao.getEvent(id);
		if (event == null)
			throw new NGOException("Event does n't exist!");
		return eventDao.deleteEvent(event);
	}

	

		@Override
	public Event getEvent(int id) {
		Event e = utilityDao.getEvent(id);
		if( e == null)
			throw new NGOException("Event does not exist!");
		e.getContributions().size();
		e.getUsers().size();
		return e;
	}

	@Override
	public List<Event> getEvents(String month, String year) {
		return eventDao.getEvents(month,year);
	}

}
