package com.qa.demo.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.domain.Dog;
import com.qa.demo.services.DogService;

@RestController // tells Spring this is a controller
// Rest compliant (representational state transfer) 

public class DogController {

	private DogService service;

	@Autowired //tells Spring to fetch the DogService from the context - dependency injection
	public DogController(DogService service) {
		super();
		this.service = service;
	}
	
	// CRUD functionality
	// ResponseEntity is an extension of HttpEntity that represents an HTTP response
	// including status code, headers and body.

	// CREATE
	@PostMapping("/create") // 201 - created
	public ResponseEntity<Dog> createDog(@RequestBody Dog d) {
		Dog created = this.service.createDog(d);
		ResponseEntity<Dog> response = new ResponseEntity<Dog>(created, HttpStatus.CREATED);
		return response;
	}

	// READ ALL
	@GetMapping("/getAll") // 200 - OK
	public ResponseEntity<List<Dog>> getAllListOfDogs() {
		return ResponseEntity.ok(this.service.getAllDogs());
	}

//READ one
	@GetMapping("/get/{id}") // 200 -OK
	public Dog getDog(@PathVariable Integer id) {
		return this.service.getDog(id);
	}

// UPDATE 
	@PutMapping("/replace/{id}") // 202 - Accepted
	public ResponseEntity<Dog> replaceDog(@PathVariable Integer id, @RequestBody Dog newDog) {
		Dog body = this.service.replaceDog(id, newDog);
		ResponseEntity<Dog> response = new ResponseEntity<Dog>(body, HttpStatus.ACCEPTED);
		return response;
	}

//DELETE 
	@DeleteMapping("/remove/{id}") // 204 - No content 
	public ResponseEntity<?> removeDog(@PathVariable Integer id) {
		this.service.removeDog(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

// READ BY NAME
	@GetMapping("/getByName/{name}") 
	public ResponseEntity<List<Dog>> getDogByName(@PathVariable String name) {
		List<Dog> found = this.service.getDogsByName(name);
		return ResponseEntity.ok(found);
	}
	
// READ BY AGE
	@GetMapping("/getByAge/{age}")
	public ResponseEntity<List<Dog>> getDogByAge(@PathVariable Integer age) {
		List<Dog> found = this.service.getDogsByAge(age);
		return ResponseEntity.ok(found);
	}

// READ BY BREED
	@GetMapping("/getByBreed/{breed}")
	public ResponseEntity<List<Dog>> getDogsByAge(@PathVariable String breed){
		List<Dog> found = this.service.getDogsByBreed(breed);
		return ResponseEntity.ok(found);
	}
}


