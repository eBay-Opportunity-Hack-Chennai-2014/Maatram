/**	Heroes Persist
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
package com.ngo.implementation.user;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ngo.exception.SportsException;
import com.ngo.interfaces.player.PlayerDao;
import com.ngo.interfaces.user.UserDao;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Game;
import com.ngo.model.Player;
import com.ngo.model.Team;
import com.ngo.model.User;

/**
 * @author Sai Pranav
 *
 */

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	UtilityDao utilityDao;

	public int addUser(User user) {
		Session session = utilityDao.getSession();
		try {
			session.save(user);
		}catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return user.getId();
	}
	
	public int modifyUser(User user) {
		Session session = utilityDao.getSession();
		try{
			session.update(user);
		}catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return user.getId();
	}

	public int deleteUser(User user) {
		Session session = utilityDao.getSession();
		try{
			session.delete(user);
		}catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return user.getId();
	}

}
