package com.sanjay.app.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sanjay.app.model.ResponseStatus;
import com.sanjay.app.model.SearchModel;
import com.sanjay.app.model.User;
import com.sanjay.app.repository.UserRepo;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	private UserRepo repo = new UserRepo();
	@POST
	@Path("/add")
	
	public ResponseStatus addUser(User u) {
		int res = repo.addUser(u);
		ResponseStatus rs = new ResponseStatus();
		if (res !=0) {
			rs.setStatus("Success");
			return rs;}
		else {
			rs.setStatus("Failure");
			return rs;}
	}

	@GET
	@Path("/view_all")
	public List<User> getUsers() {
		return repo.getUsers();
	}
	@POST
	@Path("/findUser")
	public User getUser(User u) {
		User user =  repo.searchUser(u.getEmail(),u.getPassword());
		return user;
		
	}
	@DELETE
	@Path("/delete/{userId}")
	public ResponseStatus removeUser(@PathParam("userId") int uId) {
		int res = repo.deleteUser(uId);
		ResponseStatus rs = new ResponseStatus();
		if (res !=-5) {
			rs.setStatus("Success");
			return rs;}
		else {
			rs.setStatus("Failure");
			return rs;}
	}
	@POST
	@Path("/searchUserByName")
	public List<User> serchUserByName(SearchModel sm) {
		return repo.searchUserByName(sm.getName());
	}

}
