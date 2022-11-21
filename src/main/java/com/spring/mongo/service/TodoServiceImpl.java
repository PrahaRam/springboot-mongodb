package com.spring.mongo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.spring.mongo.exception.TodoExceptionCollection;
import com.spring.mongo.model.TodoDao;
import com.spring.mongo.repository.TodoRepository;
@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;
	@Override
	public void createTodo(TodoDao todo) throws TodoExceptionCollection,ConstraintViolationException {
		// TODO Auto-generated method stub
	        
		Optional<TodoDao> createTodo=todoRepository.findByTodo(todo.getTodo());
	    if(createTodo.isPresent())
	    {
	    	throw new TodoExceptionCollection(TodoExceptionCollection.todoExsits());
	    }
	
	    
	    else
	    {
	    	todo.setCreatedAt(new Date(System.currentTimeMillis()));
	    	todoRepository.save(todo);
	    }
	}
	
	public List<TodoDao> getAllTodos() {
		// TODO Auto-generated method stub
		List<TodoDao> listTodo= todoRepository.findAll();
	    if(listTodo.size()>0)
	    {
	    	return listTodo;
	    }
	    else
	    {
	    	List<TodoDao> emptyList=new ArrayList<>();
	        return emptyList;
	    }
	
	}

	@Override
	public TodoDao getTodoById(String id) throws TodoExceptionCollection {
		// TODO Auto-generated method stub
	   Optional<TodoDao> todoById= todoRepository.findById(id);
	  if(todoById.isPresent())
	  {
		  return todoById.get();
	  }
	  else
	  {
		  throw new TodoExceptionCollection(TodoExceptionCollection.notFound(id));
	  }
	}

}
