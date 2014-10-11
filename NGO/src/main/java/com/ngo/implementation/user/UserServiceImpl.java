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
package com.ngo.implementation.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ngo.exception.SportsException;
import com.ngo.interfaces.user.UserDao;
import com.ngo.interfaces.user.UserService;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.User;
import com.ngo.webservice.user.UserForm;

/**
 * @author Sai Pranav
 *
 */

@Component
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UtilityDao utilityDao;
	
	/*User functions*/
	/**
	 * Add player with (or) without game, but if no such player exists.
	 */
	public int addUser(UserForm userForm){
		User user = utilityDao.getUser(userForm.getUsername());
		if(user != null){
			throw new SportsException("User already exists");
		}
		user = new User();
		user.setUsername(userForm.getUsername());
		user.setPassword(userForm.getPassword());
		return userDao.addUser(user);
	}

	public int modifyUser(int userId, UserForm userForm){
		User user = utilityDao.getUser(userId);
		if(user == null){
			throw new SportsException("User does not exist");
		}
		user.setUsername(userForm.getUsername());
		user.setPassword(userForm.getPassword());
		return userDao.modifyUser(user);
	}

	public int deleteUser(int userId){
		User user = utilityDao.getUser(userId);
		if(user == null){
			throw new SportsException("User does not exist");
		}
		return userDao.deleteUser(user);
	}
	
	public List<User> getUsers(){
		return utilityDao.getUsers();
	}
	
	public User checkUser(UserForm userForm){
		User tempUser = utilityDao.getUser(userForm.getUsername());
		if(tempUser != null ){
			if(tempUser.getPassword().equals(userForm.getPassword())){
				return tempUser;
			}
			else{
				throw new SportsException("Password does not match");
			}
		}
		else{
			throw new SportsException("User does not exist");
		}
	}

}
