package com.qa.demo.web;

import java.util.ArrayList;
import java.util.List;

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

@RestController // tells Spring this is a controller
// Rest compliant (representational state transfer) 

public class DogController {

	// LIST = we haven't got a DB yet so we need to store data somewhere.
	private List<Dog> ListOfDogs = new ArrayList<>();

	// CRUD functionality
	//ResponseEntity is an extension of HttpEntity that represents an HTTP response including status code, headers and body. 

	// CREATE
	@PostMapping("/create") // 201 - created
	public ResponseEntity<Dog> createDog(@RequestBody Dog d) {
		this.ListOfDogs.add(d);
		Dog created = this.ListOfDogs.get(this.ListOfDogs.size() - 1);
		ResponseEntity<Dog> response = new ResponseEntity<Dog>(created, HttpStatus.CREATED);
		return response;
	}

	// READ ALL
	@GetMapping("/getAll") // 200 - OK
	public ResponseEntity<List<Dog>> getAllListOfDogs() {
		return ResponseEntity.ok(this.ListOfDogs);
	}

//READ one
	@GetMapping("/get/{id}") // 200 -OK
	public Dog getDog(@PathVariable Integer id) {
		return this.ListOfDogs.get(id);
	}

// UPDATE 
	@PutMapping("/replace/{id}") // 202 - Accepted
	public ResponseEntity<Dog> replaceDog(@PathVariable Integer id, @RequestBody Dog newDog) {
		Dog body = this.ListOfDogs.set(id, newDog);
		ResponseEntity<Dog> response = new ResponseEntity<Dog>(body, HttpStatus.ACCEPTED);
		return response;
	}

//DELETE 
	@DeleteMapping("/remove/{id}") // 204 - No content 
	public ResponseEntity<?> removeDog(@PathVariable Integer id) {
		this.ListOfDogs.remove(id.intValue());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

//	@GetMapping("/helloWorld") // this is an endpoint
//	public String hello() {
//		return "Hello World!";
//	}
