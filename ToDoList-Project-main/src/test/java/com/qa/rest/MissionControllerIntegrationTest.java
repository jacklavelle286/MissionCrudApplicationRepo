package com.qa.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.persistence.domain.MissionDomain;
import com.qa.persistence.dto.MissionDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class MissionControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;



	private MissionDTO mapToDTO(MissionDomain model) {
		return this.mapper.map(model, MissionDTO.class);
	}

	// READ ALL TASKS
	@Test
	public void readAll() throws Exception {
		
		MissionDTO task1 = new MissionDTO(1L, "test", "test", 2, false);
		MissionDTO task2 = new MissionDTO(2L, "test", "test", 3, true);
		MissionDTO task3 = new MissionDTO(3L, "test", "test", 2, false);
		MissionDTO task4 = new MissionDTO(4L, "test", "test", 3, true);
		MissionDTO task5 = new MissionDTO(5L, "test", "test", 2, false);
		MissionDTO task6 = new MissionDTO(6L,"test", "test", 3, true);
		
				
		
		List<MissionDTO> expectedList = new ArrayList<>();
		expectedList.add(task1);
		expectedList.add(task2);
		expectedList.add(task3);
		expectedList.add(task4);
		expectedList.add(task5);
		expectedList.add(task6);

		// Request setup
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"/task/readAll");

		// Expectations setup
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedList));

		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);

	}

	// read one task test
	@Test
	public void readTask() throws Exception {
		
		MissionDTO expectedResults = new MissionDTO(1L, "test", "test", 2, false);

		// this sets up the request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"/task/read/" + 1L);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(jsonifier.writeValueAsString(expectedResults));
		// PERFORM THE ABOVE
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);

	}

	// creating task test
	@Test
	public void create() throws Exception {
		// EXPECTED INFO BACK WHICH WILL BE JSON

		MissionDomain contentBody = new MissionDomain("test2", "test2", 5, false, null);
		MissionDTO expectedResults = mapToDTO(contentBody);
		expectedResults.setId(7L);

		// sets up the request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "/task/create").contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody))
				.accept(MediaType.APPLICATION_JSON);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();

	
		ResultMatcher matchConent = MockMvcResultMatchers.content()
				.json(jsonifier.writeValueAsString(expectedResults));

		// PERFORM THE ABOVE
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchConent);
	}

	// updating task test
	@Test
	public void update() throws Exception {
		
		MissionDomain contentBody = new MissionDomain(1L, "test3", "test3", 5, false, null);
		MissionDTO expectedResults = mapToDTO(contentBody); 
		//THE request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "/task/update/" + 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status()
				.isAccepted();
		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(jsonifier.writeValueAsString(expectedResults));

		//Perform
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);

	}


	@Test
	public void delete() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE,
				"/task/delete/" + 2);

		
		ResultMatcher matchStatus = MockMvcResultMatchers.status()
				.isNoContent();

		this.mock.perform(mockRequest)
		.andExpect(matchStatus);

	}

	
	@Test
	public void deleteInternalError() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"/task/delete/" + 10);

		ResultMatcher internalerror = MockMvcResultMatchers.status().isInternalServerError();
		this.mock.perform(mockRequest)
		.andExpect(internalerror);

	}

}
