package com.qa.MissionPlannerProject.rest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.MissionPlannerProject.persistence.DataTransferObjects.MissionsDTO;
import com.qa.MissionPlannerProject.persistence.Domain.Missions;
import com.qa.MissionPlannerProject.services.MissionsService;




@RestController
@RequestMapping("/task")
public class MissionController {

	private MissionController service;
	
	
	
	@Autowired
	public MissionController(MissionsService service) {
		super();
		this.service = service;
	}

	// Read all
	@GetMapping("/readAll")
	public ResponseEntity<List<MissionsDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	
	// Read by Id
	@GetMapping("/read/{id}")
	public ResponseEntity<MissionsDTO> readmission(@PathVariable("id") Long id) {
		 return ResponseEntity.ok(this.service.readOne(id));
	}

	// Create 
	@PostMapping("/create")
	public ResponseEntity<MissionsDTO> create(@RequestBody Missions missions) {
		return new ResponseEntity<MissionsDTO>(this.service.create(missions), HttpStatus.CREATED);

	}

	// update method
	@PutMapping("/update/{id}")
	public ResponseEntity<MissionsDTO> update(@PathVariable("id") Long id, @RequestBody Missions missions) {
		return  new ResponseEntity<MissionsDTO>(this.service.update(id, missions ), HttpStatus.ACCEPTED);
	}
	
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<MissionsDTO> delete(@PathVariable("id") Long id) {
		return this.service.delete(id) ? 
				new ResponseEntity<>(HttpStatus.NO_CONTENT):
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

