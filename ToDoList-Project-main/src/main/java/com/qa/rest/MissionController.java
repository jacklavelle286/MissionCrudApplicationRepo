package com.qa.rest;

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

import com.qa.persistence.domain.MissionDomain;
import com.qa.persistence.dto.MissionDTO;
import com.qa.services.MissionService;



@RestController
@RequestMapping("/task")
public class MissionController {

	private MissionService service;



	@Autowired
	public MissionController(MissionService service) {
		super();
		this.service = service;
	}

	// READ ALL TASKS
	@GetMapping("/readAll")
	public ResponseEntity<List<MissionDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	

	// READ ID OF task
	@GetMapping("/read/{id}")
	public ResponseEntity<MissionDTO> readmission(@PathVariable("id") Long id) {
		 return ResponseEntity.ok(this.service.readOne(id));
	}

	// POST A TASK
	@PostMapping("/create")
	public ResponseEntity<MissionDTO> create(@RequestBody MissionDomain mission) {
		return new ResponseEntity<MissionDTO>(this.service.create(mission), HttpStatus.CREATED);

	}

	// UPDATE - PUT
	@PutMapping("/update/{id}")
	public ResponseEntity<MissionDTO> update(@PathVariable("id") Long id, @RequestBody MissionDomain mission) {
		return  new ResponseEntity<MissionDTO>(this.service.update(id, mission), HttpStatus.ACCEPTED);
	}
	
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		return this.service.delete(id) ? 
				new ResponseEntity<>(HttpStatus.NO_CONTENT):
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
