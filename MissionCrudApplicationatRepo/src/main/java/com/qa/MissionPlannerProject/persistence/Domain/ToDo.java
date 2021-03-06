package com.qa.MissionPlannerProject.persistence.Domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
public class ToDo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	public Long id;
	
	@OneToMany(mappedBy = "myToDo", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Missions> MissionsList;
	
	private String listName;

	public ToDo() {
		super();
	}

	public ToDo(Long id, String listName, List<Missions> MissionsList) {
		super();
		this.id = id;
		this.listName = listName;
		this.MissionsList = MissionsList;
	}

	public ToDo(Long id, String listName) {
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

	public List<Missions> getMissionList() {
		return MissionsList;
	}

	public void setMissionsList(List<Missions> MissionsList) {
		this.MissionsList = MissionsList;
	}

}

