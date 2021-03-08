package com.qa.persistence.dto;



import java.util.List;



public class ToDoDTO {
	
	private Long id;
	private String listName;

	
	public ToDoDTO() {
		super();
	}

	public ToDoDTO(Long id, String listName, List<MissionDTO> taskList) {
		super();
		this.id = id;
		this.listName = listName;

	}

	
	public ToDoDTO(Long id, String listName) {
		super();
		this.id = id;
		this.listName = listName;

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getlistName() {
		return listName;
	}

	public void setlistName(String listName) {
		this.listName = listName;
	}




}
