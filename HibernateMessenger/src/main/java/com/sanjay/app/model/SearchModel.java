package com.sanjay.app.model;

public class SearchModel {

	private String name;
	private Integer id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public SearchModel(String name, Integer id) {
		super();
		this.name = name;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SearchModel() {
		// TODO Auto-generated constructor stub
	}
}
