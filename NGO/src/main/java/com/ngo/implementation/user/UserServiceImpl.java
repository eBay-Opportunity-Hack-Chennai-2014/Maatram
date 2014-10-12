package com.ngo.implementation.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ngo.exception.NGOException;
import com.ngo.interfaces.user.UserService;
import com.ngo.interfaces.user.UserDao;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.User;
import com.ngo.webservice.user.UserForm;
import com.ngo.webservice.user.UserFormForChecking;

@Component
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UtilityDao utilityDao;
	
	/*User functions*/
	public int addUser(UserForm userForm){
		User user = utilityDao.getUser(userForm.getName(), userForm.getDob(), userForm.getAddress());
		if(user != null){
			throw new NGOException("User already exists");
		}
		user = new User();
		user.setName(userForm.getName());
		user.setCategory(userForm.getCategory());
		user.setAddress(userForm.getAddress());
		user.setGender(userForm.getGender());
		user.setEmail(userForm.getEmail());
		user.setPhone(userForm.getPhone());
		user.setPassword(userForm.getPassword());
		user.setBloodGroup(userForm.getBloodGroup());
		return userDao.addUser(user);
	}

	public int modifyUser(int userId, UserForm userForm){
		User user = utilityDao.getUser(userId);
		if(user == null){
			throw new NGOException("User does not exist");
		}
		user.setName(userForm.getName());
		user.setCategory(userForm.getCategory());
		user.setAddress(userForm.getAddress());
		user.setGender(userForm.getGender());
		user.setEmail(userForm.getEmail());
		user.setPhone(userForm.getPhone());
		user.setPassword(userForm.getPassword());
		user.setBloodGroup(userForm.getBloodGroup());
		return userDao.modifyUser(user);
	}

	public int deleteUser(int userId){
		User user = utilityDao.getUser(userId);
		if(user == null){
			throw new NGOException("User does not exist");
		}
		return userDao.deleteUser(user);
	}
	
	public List<User> getUsers(){
		return utilityDao.getUsers();
	}
	
	public User checkUser(UserFormForChecking userFormForChecking){
		User tempUser = utilityDao.getUser(userFormForChecking.getName(), userFormForChecking.getPassword());
		if(tempUser == null ){
			throw new NGOException("Invalid user or Invalid credentials");
		}
		return tempUser;
	}

}
