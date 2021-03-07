package com.qa.persistence.Repos;
import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.persistence.Domain.ToDoDomain;

public interface ToDoRepo extends JpaRepository<ToDoDomain, Long>{
	//this is the starting point for the DB and it is what the DB refers to 

}
