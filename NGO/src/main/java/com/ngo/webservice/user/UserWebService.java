package com.ngo.webservice.user;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ngo.exception.RESTException;
import com.ngo.interfaces.user.UserService;
import com.ngo.model.User;

@Component
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserWebService {
	
	@Autowired
	UserService userService;
	
	@GET
	public List<User> listUsers() {
		return userService.getUsers();
	}

	@POST
	public Response addUser(UserForm userForm) {
		int tempId;
		try{
			tempId = userService.addUser(userForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@PUT
	@Path("/{id}")
	public Response modifyUser(@PathParam("id") int id, UserForm userForm) {
		int tempId;
		try{
			tempId = userService.modifyUser(id, userForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteGame(@PathParam("id") int id) {
		int tempId;
		try{
			tempId = userService.deleteUser(id);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@POST
	@Path("/check")
	public User checkUser(UserFormForChecking userForm) {
		try{
			return userService.checkUser(userForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
	}
	
}
