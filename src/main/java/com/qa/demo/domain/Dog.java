package com.qa.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // tells Spring it's a table
public class Dog {
	//Atrributes
	
	@Id // tells Spring this is a primary key for our table
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // tells Spring that this field should be auto incremented. 
	private Integer id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String breed;
	
	@Column(nullable=false)
	private Integer age;
	
	
	//Constructors
	public Dog() {
		super();
	}

	public Dog(Integer id, String name, String breed, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.breed = breed;
		this.age = age;
	}



	// Getters and Setters 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Dog [id=" + id + ", name=" + name + ", breed=" + breed + ", age=" + age + "]";
	}
	
	
}
