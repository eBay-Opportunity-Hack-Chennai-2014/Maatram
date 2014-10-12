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
package com.ngo.implementation.utility;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ngo.exception.NGOException;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Event;
import com.ngo.model.Transaction;
import com.ngo.model.User;

/**
 * @author Sai Pranav
 *
 */
@Repository
public class UtilityDaoImpl implements UtilityDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	


	
	public User getUser(String name, String dob, String address) {
		Session session = getSession();
		List<User> userList = session.createCriteria(User.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("address", address)).add(Restrictions.eq("dob", dob)).list();
		
		if(userList.size()==1){
			return userList.get(0);
		}
		return null;
	}
	
	public User getUser(String name, String password) {
		Session session = getSession();
		if(password == null || password.isEmpty()){
			return null;
		}
		List<User> userList = session.createCriteria(User.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("password", password)).list();
		
		if(userList.size()==1){
			return userList.get(0);
		}
		return null;
	}
	
	public User getUser(int id){
		Session session = getSession();
		return (User) session.get(User.class, id);
	}
	
	public List<User> getUsers() {
		Session session = getSession();
		List<User> userList = session.createCriteria(User.class).list();
		return userList;
	}
	

	@Override
	public Event getEvent(int id) {
		Session session = getSession();
		return (Event) session.get(Event.class, id);
	}

	@Override
	public Transaction getTransaction(int id) {
		Session session = getSession();
		return (Transaction) session.get(Transaction.class, id);
	}

	@Override
	public List<Transaction> getAllTransaction() {
		Session session = getSession();
		List<Transaction> transactionList = session.createCriteria(Transaction.class).list();
		return transactionList;
	}

}
