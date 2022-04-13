package com.qa.demo.services;

import java.util.List;

public interface ServiceInterface<T> {
	
	T create(T t);
	
	List<T> getAll();
	
	T getOne(Integer id);
	
	T replace(Integer id, T t);
	
	void remove(Integer id);
}
