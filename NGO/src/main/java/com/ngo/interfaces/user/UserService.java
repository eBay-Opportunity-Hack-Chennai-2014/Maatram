package com.ngo.interfaces.user;

import java.util.List;

import com.ngo.model.User;
import com.ngo.webservice.user.UserForm;
import com.ngo.webservice.user.UserFormForChecking;

public interface UserService {

	//Core User functions
	public int addUser(UserForm userForm);
	public int modifyUser(int id, UserForm userForm);
	public int deleteUser(int id);
	
	//Utility functions
	public List<User> getUsers();
	public User checkUser(UserFormForChecking userForm);
	
}
