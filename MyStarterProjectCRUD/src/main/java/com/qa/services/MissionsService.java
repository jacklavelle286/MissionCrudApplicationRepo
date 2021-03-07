package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qa.persistence.DataTransferObjects.MissionsDTO;
import com.qa.persistence.Domain.MissionsDomain;
import com.qa.persistence.Repos.MissionsRepo;

@Service
public class MissionsService {

	private MissionsRepo repo;
	private ModelMapper mapper;

	@Autowired
	public MissionsService(MissionsRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private MissionsDTO mapToDTO(MissionsDomain model) {

		return this.mapper.map(model, MissionsDTO.class);
	}

	// get

	public List<MissionsDTO> readAll() {
		List<MissionsDomain> dbList = this.repo.findAll();
		List<MissionsDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return resultList;

	}

	// read1

	public MissionsDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}

	// post
	public MissionsDTO create(MissionsDomain task) {
		return this.mapToDTO(this.repo.save(task));
	}

	// update

	public MissionsDTO update(Long id, MissionsDomain newDetails) {
		this.repo.findById(id).orElseThrow();

		newDetails.setId(id);
		return this.mapToDTO(this.repo.save(newDetails));

	}

	// del
	public boolean delete(Long id) {
		try {
			this.repo.deleteById(id);
			boolean exists = this.repo.existsById(id);

			return !exists;

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
}
