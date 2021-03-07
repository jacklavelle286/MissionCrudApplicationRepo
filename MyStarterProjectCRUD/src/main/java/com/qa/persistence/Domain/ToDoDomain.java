package com.qa.persistence.Domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Entity
@Table(name = "TO_DO")
public class ToDoDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @NotNull
	public Long id;

	@OneToMany(mappedBy = "myToDo", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<MissionsDomain> MissionsList;

	private String listName;

	public ToDoDomain() {
		super();
	}

	public ToDoDomain(Long id, String listName, List<MissionsDomain> MissionsList) {
		super();
		this.id = id;
		this.listName = listName;
		this.MissionsList = MissionsList;
	}

	public ToDoDomain(Long id, String listName) {
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

	public List<MissionsDomain> getMissionList() {
		return MissionsList;
	}

	public void setMissionsList(List<MissionsDomain> MissionsList) {
		this.MissionsList = MissionsList;
	}

}
