package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.MissionDomain;
import com.qa.persistence.dto.MissionDTO;
import com.qa.persistence.repos.MissionRepo;

@Service
public class MissionService {

	private MissionRepo repo;
	private ModelMapper mapper;

	@Autowired
	public MissionService(MissionRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private MissionDTO mapToDTO(MissionDomain model) {
	
		return this.mapper.map(model, MissionDTO.class);
	}

	// -------- GET --------

	public List<MissionDTO> readAll() {
		List<MissionDomain> dbList = this.repo.findAll();
		List<MissionDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return resultList;

	}

	// -------- READ ONE --------

	public MissionDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}

	// -------- POST--------
	public MissionDTO create(MissionDomain task) {
		return this.mapToDTO(this.repo.save(task));
	}

	//  -------- PUT/UPDATE--------

	public MissionDTO update(Long id, MissionDomain newDetails) {
		this.repo.findById(id).orElseThrow();

		newDetails.setId(id);
		return this.mapToDTO(this.repo.save(newDetails));

	}
	
	

	// DELETE
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
