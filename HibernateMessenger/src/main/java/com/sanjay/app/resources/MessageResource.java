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

import com.sanjay.app.model.Comment;
import com.sanjay.app.model.Message;
import com.sanjay.app.model.ResponseStatus;
import com.sanjay.app.model.SearchModel;
import com.sanjay.app.model.User;
import com.sanjay.app.repository.MessageRepo;
@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	private MessageRepo repo = new MessageRepo();
	
	@POST
	@Path("/add")
	public ResponseStatus addMessage(Message msg) {
		int res = repo.addMessage(msg);
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
	public List<Message> getMessages() {
		return repo.getMessages();
	}
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") int id) {
		return repo.searchById(id);
	}
	@GET
	@Path("/{messageId}/comments")
	public List<Comment> getComments(@PathParam("messageId") int id){
		return repo.getComments(id);
	}
	@POST
	@Path("/delete")
	public ResponseStatus removeMessage(SearchModel sm) {
		int res = repo.deleteMessage(sm.getId());
		ResponseStatus rs = new ResponseStatus();
		if (res !=-5) {
			rs.setStatus("Success");
			return rs;}
		else {
			rs.setStatus("Failure");
			return rs;}
	}
	@POST
	@Path("/searchByUserId")
	public List<Message> getMessagesByuId(SearchModel sm){
		return repo.getMessagesByUserId(sm.getId());
	}
	@POST
	@Path("/searchMessageByName")
	public List<Message> serchMessageByName(SearchModel sm) {
		return repo.searchMessageByName(sm.getName());
	}
	@POST
	@Path("/editMessage")
	public ResponseStatus editMessage(SearchModel sm) {
		int res = repo.updateMessage(sm.getId(), sm.getName());
		ResponseStatus rs = new ResponseStatus();
		if(res!= 0) {
			rs.setStatus("Success");
			return rs;
		}
		else {
			rs.setStatus("Failure");
			return rs;
		}
	}
}

