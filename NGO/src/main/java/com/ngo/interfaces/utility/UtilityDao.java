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
package com.ngo.interfaces.utility;


import java.util.List;

import org.hibernate.classic.Session;

import com.ngo.model.Event;
import com.ngo.model.User;

/**
 * @author Sai Pranav
 *
 */
public interface UtilityDao {

	public Session getSession();
	
	public User getUser(String name, String dob, String address);
	public User getUser(int id);
	public List<User> getUsers();
	public User getUser(String name, String password);
	
	public Event getEvent(int id);
	
	

}