package com.qa.persistence.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.Domain.ToDoDomain;

@Repository
public interface ToDoRepo extends JpaRepository<ToDoDomain, Long> {
	// this is the starting point for the DB and it is what the DB refers to

}
