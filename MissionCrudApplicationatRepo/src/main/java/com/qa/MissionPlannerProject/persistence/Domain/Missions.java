package com.qa.MissionPlannerProject.persistence.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class Missions {
//these are the columns in the s table 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;

	@NotNull
	private String nameofMission;

	@NotNull
	private String responsibility;

	@NotNull
	private Integer orderofimportance;

	@NotNull
	private Boolean completedstatus;
//this maps the many to one relationship of the s and the to do tables together 
	@ManyToOne
	private ToDo myToDo;

	public Missions() {
		super();
		
	}
	
	public Missions(Long id, String nameof, String responsibility, Integer orderofimportance, Boolean completed, ToDo myToDo) {
		super();
		this.id = id;
		this.nameofMission = nameofMission;
		this.responsibility = responsibility;
		this.orderofimportance = orderofimportance;
		this.completedstatus = completedstatus;
		this.myToDo = myToDo;

	}


//same but minus the ID as the id is my primary key, and auto_incremented too
	public Missions( String nameof, String categoryof, Integer orderofimportance, Boolean completed, ToDo myToDo) {
		super();
		this.nameofMission = nameofMission;
		this.responsibility = responsibility;
		this.orderofimportance = orderofimportance;
		this.completedstatus = completedstatus;
		this.myToDo = myToDo;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getnameofMission() {
		return nameofMission;
	}

	public void setnameofMission(String nameofMission) {
		this.nameofMission = nameofMission;
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

	public void setorderofimportance(int orderofimportance) {
		this.orderofimportance = orderofimportance;
	}

	public Boolean completedstatus() {
		return completedstatus;
	}

	public void setcompletedstatus(Boolean completedstatus) {
		this.completedstatus = completedstatus;
	}

	public ToDo getMyToDo() {
		return myToDo;
	}

	public void setMyToDo(ToDo myToDo) {

		this.myToDo = myToDo;
	}

}
