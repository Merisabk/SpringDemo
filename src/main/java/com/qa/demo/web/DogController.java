package com.qa.demo.web;

import java.util.ArrayList;
import java.util.List;

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

	// CREATE
	@PostMapping("/create")
	public Dog createDog(@RequestBody Dog d) {
		this.ListOfDogs.add(d);
		Dog created = this.ListOfDogs.get(this.ListOfDogs.size() - 1);
		return created;
	}

	// READ ALL
	@GetMapping("/getAll")
	public List<Dog> getAllListOfDogs() {
		return this.ListOfDogs;
	}

//READ one
	@GetMapping("/get/{id}")
	public Dog getDog(@PathVariable Integer id) {
		return this.ListOfDogs.get(id);
	}

// UPDATE 
	@PutMapping("/replace/{id}")
	public Dog replaceDog(@PathVariable Integer id, @RequestBody Dog newDog) {
		Dog body = this.ListOfDogs.set(id, newDog);
		return body;

	}

//DELETE 
	@DeleteMapping("/remove/{id}")
	public void removeDog(@PathVariable Integer id) {
		this.ListOfDogs.remove(id.intValue());
	}
}

//	@GetMapping("/helloWorld") // this is an endpoint
//	public String hello() {
//		return "Hello World!";
//	}
