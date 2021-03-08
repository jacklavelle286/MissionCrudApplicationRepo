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

import com.qa.persistence.domain.ToDoDomain;
import com.qa.persistence.dto.ToDoDTO;
import com.qa.services.ToDoService;



@RestController
@RequestMapping("/todo")
public class ToDoController {

	private ToDoService service;


	@Autowired
	public ToDoController(ToDoService service) {
		super();
		this.service = service;
	}

	// READ ALL TODOLISTS
	@GetMapping("/readAll")
	public ResponseEntity<List<ToDoDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	

	// READ ID OF TODOLISTS
	@GetMapping("/read/{id}")
	public ResponseEntity<ToDoDTO> readTODOLISTS(@PathVariable("id") Long id) {
		 return ResponseEntity.ok(this.service.readOne(id));
	}

	// POST AN ITEM
	@PostMapping("/create")
	public ResponseEntity<ToDoDTO> create(@RequestBody ToDoDomain todo) {
		return new ResponseEntity<ToDoDTO>(this.service.create(todo), HttpStatus.CREATED);

	}

	// UPDATE - PUT
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoDTO> update(@PathVariable("id") Long id, @RequestBody ToDoDomain todo) {
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
