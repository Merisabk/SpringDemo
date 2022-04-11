package com.qa.demo.domain;

public class Dog {
	//Atrributes
	private String name;
	private String breed;
	private Integer age;
	
	//Constructors
	public Dog() {
		super();
	}

	public Dog(String name, String breed, Integer age) {
		super();
		this.name = name;
		this.breed = breed;
		this.age = age;
	}

	// Getters and Setters 
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

	// To String method
	@Override
	public String toString() {
		return "Dog [name=" + name + ", breed=" + breed + ", age=" + age + "]";
	}
	
	

	
}
