package com.qa.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.demo.domain.Dog;

@Repository 
public interface DogRepo extends JpaRepository<Dog, Integer> {
	
	//Spring will auto-generate all of the basic CRUD functionality. 
	
	List<Dog> findByNameIgnoreCase(String name);
	List<Dog> findByAge(Integer age);
	List<Dog> findByBreedIgnoreCase(String breed);
}
