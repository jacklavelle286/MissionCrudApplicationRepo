package com.qa.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.qa.persistence.domain.MissionDomain;
import com.qa.persistence.dto.MissionDTO;
import com.qa.persistence.repos.MissionRepo;

@SpringBootTest
public class MissionServiceUnitTest {

	@MockBean // @Mock
	private ModelMapper MockMapper;
	@MockBean // @Mock
	private MissionRepo mockRepo;

	@Autowired // @injectMocks
	private MissionService service;
	

	
	
	//Create
	
	@Test
	public void create() {
		
		//needed info
		MissionDomain TEST_TASK = new MissionDomain("Help Mum", "Helping", 5, false, null);
		MissionDTO DTOtest = new MissionDTO(1L, "Buy Banana", "Shopping", 2, false);

		//rule
		Mockito.when(this.mockRepo.save(Mockito.any(MissionDomain.class))).thenReturn(TEST_TASK);
		Mockito.when(this.MockMapper.map(TEST_TASK, MissionDTO.class)).thenReturn(DTOtest);


		MissionDTO result = this.service.create(TEST_TASK);


		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(DTOtest);
		Assertions.assertThat(result).usingRecursiveComparison()
		.isEqualTo(DTOtest);
		
		Mockito.verify(this.mockRepo, Mockito.times(1)).save(Mockito.any(MissionDomain.class));
		//should only run once
		Mockito.verify(this.MockMapper, Mockito.times(1)).map(TEST_TASK, MissionDTO.class);

	}
	
//	READ ONE
	
	
	
	@Test
	public void readOne() {
		
		MissionDomain test_task = new MissionDomain(1L, "Help Mum", "Helping", 5, false, null);
		MissionDTO test_dto = this.MockMapper.map(test_task, MissionDTO.class);
		
		//the rules
		Mockito.when(this.mockRepo.findById(test_task.getId())).thenReturn(Optional.of(test_task));
		
		//the task
		MissionDTO result = this.service.readOne(1L);

		
		Assertions.assertThat(result).isEqualTo(test_dto);
		//should only run once
		Mockito.verify(this.mockRepo, Mockito.times(1)).findById(1L);
		
		

	}
	
//	READ ALL
	
	@Test
	public void readAll() {
		
		MissionDomain testTask = new MissionDomain(1L, "Help Mum", "Helping", 5, false, null);
		List<MissionDomain> testList =  new ArrayList<>();
		testList.add(testTask);

		MissionDTO testDTO = new MissionDTO(1L, "Buy Banana", "Shopping", 2, false);
		List<MissionDTO> testDTOList = new ArrayList<>();
		testDTOList.add(testDTO);

		// Rules
		Mockito.when(this.mockRepo.findAll() ).thenReturn(testList);
		Mockito.when(this.MockMapper.map(testTask, MissionDTO.class)).thenReturn(testDTO);

		// Actions

		List<MissionDTO> result = this.service.readAll();

		// Assertions

		Assertions.assertThat(result).usingRecursiveComparison()
		.isEqualTo(testDTOList);
		

	}
	
	
//	DELETE
	@Test
	public void delete() {
		
		//resources
		MissionDomain test_task = new MissionDomain(1L, "Help Mum", "Helping", 5, false, null);
		MissionDTO testDTO = new MissionDTO(1L, "Buy Banana", "Shopping", 2, false);
		//rules
		Mockito.when(this.mockRepo.existsById(test_task.getId())).thenReturn(true);
		
		//assertions
		Assertions.assertThat(this.service.delete(testDTO.getId())).isEqualTo(false);

		//verify it has ran once each
		Mockito.verify(this.mockRepo, Mockito.times(1)).deleteById(test_task.getId());
		Mockito.verify(this.mockRepo, Mockito.times(1)).existsById(test_task.getId());

	}
	
}