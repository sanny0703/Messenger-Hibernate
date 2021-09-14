package com.sanjay.app.model;

public class MessageComment {

	private String message;
	private String comment;
	private Integer id;
	private Integer cId;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	public MessageComment(String message, String comment, Integer id, Integer cId) {
		super();
		this.message = message;
		this.comment = comment;
		this.id = id;
		this.cId = cId;
	}
	public MessageComment() {
		// TODO Auto-generated constructor stub
	}
}
