package com.qa.persistence.DataTransferObjects;

public class MissionsDTO {

	private Long id;
	private String nameofMission;
	private String responsibility;
	private Integer orderofimportance;
	private Boolean completedstatus;

	public MissionsDTO() {
		super();
		
	}
	

	public MissionsDTO(Long id, String nameofMission, String responsibility, int orderofimportance, Boolean completedstatus) {
		super();
		this.id = id;
		this.nameofMission = nameofMission;
		this.responsibility = responsibility;
		this.orderofimportance = orderofimportance;
		this.completedstatus = completedstatus;
	}

	public Long getId() {
		return id;
	}

	public void set(Long id) {
		this.id = id;
	}

	public String getnameofMission() {
		return nameofMission;
	}

	public void setnameofMission(String nameofMission) {
		this.nameofMission = nameofMission;
	}

	public Boolean completedstatus() {
		return completedstatus;
	}

	public void setcompletedstatus(Boolean completedstatus) {
		this.completedstatus = completedstatus;
	}


	public String getresponsibility() {
		return responsibility;
	}


	public void setresponsibility(String responsibility) {
		this.responsibility = responsibility;
	}


	public int getorderofimportance() {
		return orderofimportance;
	}


	public void setorderofimportance(int rank) {
		this.orderofimportance = orderofimportance;
	}



}
