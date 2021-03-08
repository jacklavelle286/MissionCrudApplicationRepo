package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class MissionDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;

	@NotNull
	private String nameofmission;

	@NotNull
	private String responsibility;

	@NotNull
	private Integer orderofimportance;

	@NotNull
	private Boolean completedstatus;

	@ManyToOne
	private ToDoDomain myToDo;

	public MissionDomain() {
		super();
		
	}
	
	public MissionDomain(Long id, String nameofmission, String responsibility, Integer orderofimportance, Boolean completedstatus, ToDoDomain myToDo) {
		super();
		this.id = id;
		this.nameofmission = nameofmission;
		this.responsibility = responsibility;
		this.orderofimportance = orderofimportance;
		this.completedstatus = completedstatus;
		this.myToDo = myToDo;

	}

	public MissionDomain(String nameofmission, String responsibility, Integer orderofimportance, Boolean completedstatus, ToDoDomain myToDo) {
		super();
		this.nameofmission = nameofmission;
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

	public String getNameofmission() {
		return nameofmission;
	}

	public void setNameofmission(String nameofmission) {
		this.nameofmission = nameofmission;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public Integer getOrderofimportance() {
		return orderofimportance;
	}

	public void setOrderofimportance(Integer orderofimportance) {
		this.orderofimportance = orderofimportance;
	}

	public Boolean getCompletedstatus() {
		return completedstatus;
	}

	public void setompletedstatus(Boolean completedstatus) {
		this.completedstatus = completedstatus;
	}

	public ToDoDomain getMyToDo() {
		return myToDo;
	}

	public void setMyToDo(ToDoDomain myToDo) {

		this.myToDo = myToDo;
	}

}