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


import com.qa.persistence.domain.ToDoDomain;
import com.qa.persistence.dto.ToDoDTO;
import com.qa.persistence.repos.ToDoRepo;

@SpringBootTest
public class ToDoServiceUnitTest {

	@MockBean // @Mock
	private ModelMapper MockMapper;
	@MockBean // @Mock
	private ToDoRepo mockRepo;

	@Autowired // @injectMocks
	private ToDoService service;
	

	
	
	//Create
	
	@Test
	public void create() {
		
		ToDoDomain TEST_TASK = new ToDoDomain(1L, "ToDo");
		ToDoDTO DTOtest = new ToDoDTO(1L, "ToDo");

		Mockito.when(this.mockRepo.save(Mockito.any(ToDoDomain.class))).thenReturn(TEST_TASK);
		Mockito.when(this.MockMapper.map(TEST_TASK, ToDoDTO.class)).thenReturn(DTOtest);


		ToDoDTO result = this.service.create(TEST_TASK);


		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(DTOtest);
		Assertions.assertThat(result).usingRecursiveComparison()
		.isEqualTo(DTOtest);
		
		Mockito.verify(this.mockRepo, Mockito.times(1)).save(Mockito.any(ToDoDomain.class));
		//should only run once
		Mockito.verify(this.MockMapper, Mockito.times(1)).map(TEST_TASK, ToDoDTO.class);

	}
	
//	READ ONE
	
	
	
	@Test
	public void readOne() {
		
		ToDoDomain test_task = new ToDoDomain(1L, "ToDo");
		ToDoDTO test_dto = this.MockMapper.map(test_task, ToDoDTO.class);
		
		//the rules
		Mockito.when(this.mockRepo.findById(test_task.getId()))
		.thenReturn(Optional.of(test_task));
		
		//the task
		ToDoDTO result = this.service.readOne(1L);

		
		Assertions.assertThat(result).isEqualTo(test_dto);
		//should only run once
		Mockito.verify(this.mockRepo, Mockito.times(1)).findById(1L);
		
		

	}
	
//	READ ALL
	
	@Test
	public void readAll() {
		
		ToDoDomain testTask = new ToDoDomain(1L, "Help Mum");
		List<ToDoDomain> testList =  new ArrayList<>();
		testList.add(testTask);

		
		ToDoDTO testDTO = new ToDoDTO(1L, "Buy Banana");
		List<ToDoDTO> testDTOList = new ArrayList<>();
		testDTOList.add(testDTO);

		// Rules
		Mockito.when(this.mockRepo.findAll() ).thenReturn(testList);
		Mockito.when(this.MockMapper.map(testTask, ToDoDTO.class)).thenReturn(testDTO);

		// Actions

		List<ToDoDTO> result = this.service.readAll();

		// Assertions

		Assertions.assertThat(result).usingRecursiveComparison()
		.isEqualTo(testDTOList);
		

	}
	
	
//	DELETE
	@Test
	public void delete() {
		
		//resources
		ToDoDomain test_task = new ToDoDomain(1L, "ToDo");
		ToDoDTO testDTO = new ToDoDTO(1L, "Buy Banana");
		//rules
		Mockito.when(this.mockRepo.existsById(test_task.getId())).thenReturn(true);
		
		//assertions
		Assertions.assertThat(this.service.delete(testDTO.getId())).isEqualTo(false);

		//verify it has ran once each
		Mockito.verify(this.mockRepo, Mockito.times(1)).deleteById(test_task.getId());
		Mockito.verify(this.mockRepo, Mockito.times(1)).existsById(test_task.getId());

	}
	
}