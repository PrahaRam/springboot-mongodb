package com.spring.mongo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import com.spring.mongo.exception.TodoExceptionCollection;
import com.spring.mongo.model.TodoDao;

public interface TodoService {


	void createTodo(TodoDao todo) throws TodoExceptionCollection, ConstraintViolationException;
	
   List<TodoDao> getAllTodos();
   
   TodoDao getTodoById(String id) throws TodoExceptionCollection;



}
