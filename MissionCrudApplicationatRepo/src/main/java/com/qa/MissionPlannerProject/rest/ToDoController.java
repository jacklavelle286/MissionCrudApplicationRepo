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

import com.qa.MissionPlannerProject.persistence.DataTransferObjects.ToDoDTO;
import com.qa.MissionPlannerProject.persistence.Domain.ToDo;
import com.qa.MissionPlannerProject.services.ToDoService;



@RestController
@RequestMapping("/todo")
public class ToDoController {
	
	private ToDoService service;

	@Autowired
	public ToDoController(ToDoService service) {
		super();
		this.service = service;
	}

	// read all
	@GetMapping("/readAll")
	public ResponseEntity<List<ToDoDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	

	// read id
	@GetMapping("/read/{id}")
	public ResponseEntity<ToDoDTO> readTODOLISTS(@PathVariable("id") Long id) {
		 return ResponseEntity.ok(this.service.readOne(id));
	}

	// create
	@PostMapping("/create")
	public ResponseEntity<ToDoDTO> create(@RequestBody ToDo todo) {
		return new ResponseEntity<ToDoDTO>(this.service.create(todo), HttpStatus.CREATED);

	}

	// UPDATE - PUT
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoDTO> update(@PathVariable("id") Long id, @RequestBody ToDo todo) {
		return  new ResponseEntity<ToDoDTO>(this.service.update(id, todo), HttpStatus.ACCEPTED);
	}
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		return this.service.delete(id) ? 
				new ResponseEntity<>(HttpStatus.NO_CONTENT):
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}


}

