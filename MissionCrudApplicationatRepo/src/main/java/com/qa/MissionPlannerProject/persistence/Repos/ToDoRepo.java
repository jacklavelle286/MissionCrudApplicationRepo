package com.qa.MissionPlannerProject.persistence.Repos;
import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.MissionPlannerProject.persistence.Domain.ToDo;

public interface ToDoRepo extends JpaRepository<ToDo, Long>{
	//this is the starting point for the DB and it is what the DB refers to 

}
