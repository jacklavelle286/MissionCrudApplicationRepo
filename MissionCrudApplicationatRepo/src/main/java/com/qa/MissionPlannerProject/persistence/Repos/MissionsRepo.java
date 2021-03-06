package com.qa.MissionPlannerProject.persistence.Repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.MissionPlannerProject.persistence.Domain.Missions;

@Repository
public interface MissionsRepo extends JpaRepository<Missions, Long>{
	//this is the starting point for the DB and it is what the DB refers to 

}
