package com.qa.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.sun.istack.NotNull;

@Entity
public class ToDoDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	public Long id;
	
	@OneToMany(mappedBy = "myToDo", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<MissionDomain> missionList;
	
	private String listNameofmission;

	public ToDoDomain() {
		super();
	}

	public ToDoDomain(Long id, String listnameofmission, List<MissionDomain> missionList) {
		super();
		this.id = id;
		this.listNameofmission = listnameofmission;
		this.missionList = missionList;
	}

	public ToDoDomain(Long id, String listnameofmission) {
		super();
		this.id = id;
		this.listNameofmission = listnameofmission;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getlistNameofmission() {
		return listNameofmission;
	}

	public void setlistNameofmission(String listNameofmission) {
		this.listNameofmission = listNameofmission;
	}

	public List<MissionDomain> getmissionList() {
		return missionList;
	}

	public void setTaskList(List<MissionDomain> missionList) {
		this.missionList = missionList;
	}

}
