package com.ngo.interfaces.user;

import com.ngo.model.User;

public interface UserDao {

	public int addUser(User user);
	public int modifyUser(User user);
	public int deleteUser(User user);
	
}
