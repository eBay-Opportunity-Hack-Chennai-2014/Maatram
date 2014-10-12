package com.ngo.implementation.event;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ngo.exception.NGOException;
import com.ngo.interfaces.event.EventDao;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Event;

/**
 * @author Sai Pranav
 *
 */

@Repository
public class EventDaoImpl implements EventDao{
	
	@Autowired
	UtilityDao utilityDao;

	public int addEvent(Event event) {
		Session session = utilityDao.getSession();
		try {
			session.save(event);
		}catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		return event.getId();
	}
	
	public int updateEvent(Event event) {
		Session session = utilityDao.getSession();
		try{
			session.update(event);
		}catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		return event.getId();
	}

	public List<Event> getEvents(String month,String year){
		Session session = utilityDao.getSession();
		try{
			int iMonth = Integer.parseInt(month);
			int iYear = Integer.parseInt(year);
			Criteria cr= session.createCriteria(Event.class);
			cr = cr.add(Restrictions.eq("month", iMonth));
			cr = cr.add(Restrictions.eq("year", iYear));
			return cr.list();
			
			
		}catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
//	public List<Event> getEvents(int org_id){
//		
//		Session session = utilityDao.getSession();
//		try{
//			Criteria cr = session.createCriteria(Organization.class);
//			cr.add(Restrictions.eq("id", org_id));
//			List<Organization> lstOrg = cr.list();
//			int numOrgs = lstOrg.size();
//			int i=0;
//			List<Event> lstEvent = new ArrayList<Event>();
//			for(Organization anOrg=lstOrg.get(0);i< numOrgs;i++){
//				lstEvent.addAll(anOrg.getEvents());
//			}
//			
//			return lstEvent;
//			
//		}catch(HibernateException e){
//			throw new NGOException("OOPS! There seems to be a problem with the database");
//		}
//	}
	
	public int deleteEvent(Event event) {
		Session session = utilityDao.getSession();
		try{
			session.delete(event);
		}catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		return event.getId();
	}

}
