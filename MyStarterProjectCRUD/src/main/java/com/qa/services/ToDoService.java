package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.qa.persistence.DataTransferObjects.ToDoDTO;
import com.qa.persistence.Domain.ToDoDomain;
import com.qa.persistence.Repos.ToDoRepo;

public class ToDoService {


	private ToDoRepo repo;
	private ModelMapper mapper;

	@Autowired
	public ToDoService(ToDoRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private ToDoDTO mapToDTO(ToDoDomain model) {
	
		return this.mapper.map(model, ToDoDTO.class);
	}
	// get
	public List<ToDoDTO> readAll() {
		List<ToDoDomain> dbList = this.repo.findAll();
	List<ToDoDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());
	return resultList;

	}

	// -------- READ ONE --------

	public ToDoDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}

	//	//post
	public ToDoDTO create(ToDoDomain model) {
		return this.mapToDTO(this.repo.save(model));
	}

	// update

	public ToDoDTO update(Long id, ToDoDomain newDetails) {
		this.repo.findById(id).orElseThrow();

		newDetails.setId(id);
		return this.mapToDTO(this.repo.save(newDetails));

	}
	
	

	// delete
	public boolean delete(Long id) {
		  try {
		        this.repo.deleteById(id);
		        boolean exists = this.repo.existsById(id);

		 

		        return !exists;
		        
		        }catch(EmptyResultDataAccessException e) {
		            e.printStackTrace();
		            return false;
		        }
		    }
	}

