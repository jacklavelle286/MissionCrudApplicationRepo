package com.qa.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.MissionDomain;


@Repository
public interface MissionRepo extends JpaRepository<MissionDomain, Long>{
	
	//CRUD -> H2 DATABASE

}
