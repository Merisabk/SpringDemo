package com.qa.demo.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.demo.domain.Dog;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the MockMvc object
@Sql(scripts = {"classpath:dog-schema.sql", "classpath:dog-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class DogControllerIntegrationTest {
	
	@Autowired  // this pulls the MockMvc object from the context
	private MockMvc mvc;  // class that performs the request (kind of a postman equivalent)
	
	@Autowired 
	private ObjectMapper mapper;  // java <-> JSON converter that Spring uses 
	
	@Test
	void testCreate() throws Exception {
		Dog testDog = new Dog(null, "Jo", "pug", 4);
		String testDogAsJSON = this.mapper.writeValueAsString(testDog); 
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testDogAsJSON);
		
		Dog testcreatedDog = new Dog(3, "Jo", "pug", 4);
		String testcreatedDogAsJSON = this.mapper.writeValueAsString(testcreatedDog);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testcreatedDogAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test 
	void testGetAll() throws Exception {
		RequestBuilder req = get("/getAll");
		
		List<Dog> testDogs = List.of(new Dog(1, "Joy", "pug", 3), new Dog(2, "Puro","Shiba", 5));
		String json = this.mapper.writeValueAsString(testDogs);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	
	}
	
	@Test
	void testGetById() throws Exception {
		RequestBuilder req = get("/get/1");
		
		Dog testDogById = new Dog(1, "Joy", "pug", 3);
		String json = this.mapper.writeValueAsString(testDogById);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		}
	
	@Test
	void testReplace() throws Exception {
		Dog testDog = new Dog(null, "Joy", "pug", 3);
		String testDogAsJSON = this.mapper.writeValueAsString(testDog); 
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testDogAsJSON);
	
		Dog testReplacedDog = new Dog (1,"Joy", "pug", 3);
		String testReplacedDogAsJSON = this.mapper.writeValueAsString(testReplacedDog);
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testReplacedDogAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test 
	void testRemove() throws Exception {
		
	//	this.mvc.perform(delete("/remove/1")).andExpect(status().isNoContent());
		RequestBuilder req = delete("/remove/1");
		
		ResultMatcher checkStatus = status().isNoContent();
		
		this.mvc.perform(req).andExpect(checkStatus);
	}
	
	@Test
	void testGetByName() throws Exception {
		RequestBuilder req = get("/getByName/Joy");
	
		List<Dog> testDogByName = List.of(new Dog(1, "Joy", "pug", 3));
		String json = this.mapper.writeValueAsString(testDogByName);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetByAge() throws Exception {
		RequestBuilder req = get("/getByAge/3");
		
		List<Dog> testDogByAge = List.of(new Dog (1, "Joy", "pug", 3));
		String json = this.mapper.writeValueAsString(testDogByAge);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetByBreed() throws Exception {
		RequestBuilder req = get("/getByBreed/pug");
		
		List<Dog> testDogByBreed = List.of(new Dog (1, "Joy", "pug", 3));
		String json = this.mapper.writeValueAsString(testDogByBreed);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
}
