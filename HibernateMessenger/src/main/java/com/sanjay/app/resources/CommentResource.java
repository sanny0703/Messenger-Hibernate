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
import com.sanjay.app.model.ResponseStatus;
import com.sanjay.app.model.SearchModel;
import com.sanjay.app.repository.CommentRepo;

@Path("comments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentRepo repo = new CommentRepo();
	
	@POST
	@Path("/add")
	public ResponseStatus addComment(Comment cm) {
		int res = repo.addComment(cm);
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
	public List<Comment> getComments() {
		return repo.getComments();
	}
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("commentId") int cId) {
		return repo.searchById(cId);
	}
	@POST
	@Path("/delete")
	public ResponseStatus removeComment(SearchModel sm) {
		int res = repo.deleteComment(sm.getId());
		ResponseStatus rs = new ResponseStatus();
		if (res !=-5) {
			rs.setStatus("Success");
			return rs;}
		else {
			rs.setStatus("Failure");
			return rs;}
	}
	@POST
	@Path("/getCommentsByMessageId")
	public List<Comment> getCommentsById(SearchModel sm){
		return repo.getCommentsByMessageId(sm.getId());
	}

}
