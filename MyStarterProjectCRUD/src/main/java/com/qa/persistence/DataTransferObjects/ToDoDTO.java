package com.qa.persistence.DataTransferObjects;

import java.util.List;

import com.qa.persistence.DataTransferObjects.MissionsDTO;


public class ToDoDTO {

	
	private Long id;
	private String listnameofMission;

	
	public ToDoDTO() {
		super();
	}

	public ToDoDTO(Long id, String listnameofMission, List<MissionsDTO> missionslist) {
		super();
		this.id = id;
		this.listnameofMission = listnameofMission;

	}

	
	public ToDoDTO(Long id, String listnameofMission) {
		super();
		this.id = id;
		this.listnameofMission = listnameofMission;

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getlistnameofMission() {
		return listnameofMission;
	}

	public void setlistnameofMission(String listnameofMission) {
		this.listnameofMission = listnameofMission;
	}




}
