package com.qa.persistence.dto;


public class MissionDTO {

	private Long id;
	private String name;
	private String category;
	private Integer rank;
	private Boolean completed;

	public MissionDTO() {
		super();
		
	}
	
	
	//remove what you want to protect it from being exposed
	public MissionDTO(Long id, String name, String category, Integer rank, Boolean completed) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.rank = rank;
		this.completed = completed;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Integer getRank() {
		return rank;
	}


	public void setRank(Integer rank) {
		this.rank = rank;
	}



}
