package com.qa.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.demo.domain.Dog;
import com.qa.demo.repo.DogRepo;

@Service 
public class DogService {

	private DogRepo repo;
	
	@Autowired
	public DogService(DogRepo repo) {
		super();
		this.repo = repo;
	}

	// CRUD 
	// INSERT INTO Dog
	public Dog createDog(Dog d) {
		Dog created = this.repo.save(d);
		return created;
	}
	
	// SELECT * FROM Dog
	public List<Dog> getAllDogs() {
		return this.repo.findAll();
	}
	
	// SELECT * FROM Dog WHERE ID = ? 
	public Dog getDog(Integer id) {
		Optional<Dog> found = this.repo.findById(id);
		return found.get();
	}
	
	// UPDATE
	public Dog replaceDog (Integer id, Dog newD) {
		Dog existing = this.repo.findById(id).get();
		existing.setAge(newD.getAge());
		existing.setName(newD.getName());
		existing.setBreed(newD.getBreed());
		Dog updated = this.repo.save(existing);
		return updated;
	}
	
	// DELETE FROM DOG WHERE ID = ?
	public void removeDog(@PathVariable Integer id) {
		this.repo.deleteById(id);
	}
	
	//SELECT * FROM Dog WHERE name = ?
	public List<Dog> getDogsByName (String name) {
		List<Dog> found = this.repo.findByNameIgnoreCase(name);
		return found;
	}
	
	//SELECT * FROM Dog WHERE age = ?
	public List<Dog> getDogsByAge(Integer age){
		List<Dog> found = this.repo.findByAge(age);
		return found;
	}

	// SELECT * FROM Dog WHERE breed =? 
	public List<Dog> getDogsByBreed(String breed){
		List<Dog> found = this.repo.findByBreedIgnoreCase(breed);
		return found;
	}
}
