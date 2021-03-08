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
import com.qa.persistence.domain.ToDoDomain;
import com.qa.persistence.dto.ToDoDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ToDoControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;



	private ToDoDTO mapToDTO(ToDoDomain model) {
		return this.mapper.map(model, ToDoDTO.class);
	}

	// READ ALL todoS
	@Test
	public void readAll() throws Exception {
		
		ToDoDTO todo1 = new ToDoDTO(1L, "Helping");
		ToDoDTO todo2 = new ToDoDTO(2L, "Shopping");
		ToDoDTO todo3 = new ToDoDTO(3L, "Work");
		ToDoDTO todo4 = new ToDoDTO(4L, "Work");
		ToDoDTO todo5 = new ToDoDTO(5L, "Shopping");
		ToDoDTO todo6 = new ToDoDTO(6L,"House Items");
		
				
		
		List<ToDoDTO> expectedList = new ArrayList<>();
		expectedList.add(todo1);
		expectedList.add(todo2);
		expectedList.add(todo3);
		expectedList.add(todo4);
		expectedList.add(todo5);
		expectedList.add(todo6);

		// Request setup
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"/todo/readAll");

		// Expectations setup
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedList));

		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);


	}

	//read one todo test
	@Test
	public void readtodo() throws Exception {
		
        // EXPECTED INFO BACK WHICH WILL BE JSON
        ToDoDTO expectedResults = new ToDoDTO(1L, "Helping");

        // this sets up the request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
                "/todo/read/" + 1L);

        // CHECK STATUS THAT YOU GET
        ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();

        // CHECK IF YOU GET ONE todo
        ResultMatcher matchConent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResults));

        // PERFORM THE ABOVE
        this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchConent);
	}
	
	
	//creating todo test
	@Test
	public void create() throws Exception {
        // EXPECTED INFO BACK WHICH WILL BE JSON

        ToDoDomain contentBody = new ToDoDomain(7L, "ToDo");
        ToDoDTO expectedResults = mapToDTO(contentBody); // EXPECTED RESULT
        expectedResults.setId(7L);

        // this sets up the request
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .request(HttpMethod.POST, "/todo/create").contentType(MediaType.APPLICATION_JSON)
                .content(jsonifier.writeValueAsString(contentBody)).accept(MediaType.APPLICATION_JSON);

        // CHECK STATUS THAT YOU GET
        ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();

        // CHECK IF YOU CREATED todo
        ResultMatcher matchConent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResults));

        // PERFORM THE ABOVE
        this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchConent);
	}

	//updating todo test
	@Test
	public void update() throws Exception {
		
		ToDoDomain test_task = new ToDoDomain(1L, "Help Mum");
		ToDoDTO expected = mapToDTO(test_task);
		

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "/todo/update/" + 1L)
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(test_task)).accept(MediaType.APPLICATION_JSON);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expected));

		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		

	}

	//delete todo test successful
	@Test
	public void delete() throws Exception {
		
        // this sets up the request
        // we are going to delete ID 2
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
                "/todo/delete/" + 2);

        // CHECK STATUS THAT YOU GET
        ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

        // PERFORM THE ABOVE
        this.mock.perform(mockRequest).andExpect(matchStatus);

	}
	//delete todo test exception
	@Test
	public void deleteInternalError() throws Exception {

            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
                    "/todo/delete/" + 10);

            ResultMatcher nonMatch = MockMvcResultMatchers.status().isInternalServerError();
            this.mock.perform(mockRequest).andExpect(nonMatch);

	}

}
