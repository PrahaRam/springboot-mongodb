package com.spring.mongo.TodoDaoController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mongo.exception.TodoExceptionCollection;
import com.spring.mongo.model.TodoDao;
import com.spring.mongo.repository.TodoRepository;
import com.spring.mongo.service.TodoService;

@RestController
public class TodoDaoController {

	@Autowired
	private TodoRepository todoRepository;
	@Autowired
	private TodoService todoService;
	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos()
	{
		List<TodoDao> todos= todoService.getAllTodos();
	 
		return new ResponseEntity<List<TodoDao>>(todos,todos.size()>0?HttpStatus.OK:HttpStatus.NOT_FOUND);
		
		
	}
   
	@PostMapping("/todos")
	public ResponseEntity<?> createTodos(@RequestBody TodoDao todos)
	{
		try {
			 todoService.createTodo(todos);
			 
	        return new ResponseEntity<TodoDao>(todos,HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (TodoExceptionCollection e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
	
	
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getTodoById(@PathVariable String id)
	{
		try {
			return new ResponseEntity<>(todoService.getTodoById(id),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/todos/{id}")
	public ResponseEntity<?> updateById(@PathVariable String id,@RequestBody TodoDao todo)
	{
		Optional<TodoDao> todoById = todoRepository.findById(id);
	    if(todoById.isPresent())
	    {
	    	TodoDao updateTodo = todoById.get();
	    	updateTodo.setId(todo.getId()!=null?todo.getId():updateTodo.getId());
	    	updateTodo.setTodo(todo.getTodo()!=null?todo.getTodo():updateTodo.getTodo());
	    	updateTodo.setDescription(todo.getDescription()!=null?todo.getDescription():updateTodo.getDescription());
	    	updateTodo.setCompleted(todo.getCompleted()!=null?todo.getCompleted():updateTodo.getCompleted());
	    	updateTodo.setUpdatedAt(new Date(System.currentTimeMillis()));
	    	todoRepository.save(updateTodo);
	    	return new ResponseEntity<TodoDao>(updateTodo,HttpStatus.OK);
	    }
	    else
	    {
	    	return new ResponseEntity<String>("todo is not found " +id ,HttpStatus.NOT_FOUND);
	    }  
	}

	@DeleteMapping("/todos/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id)
	{
	 try {
		   Optional<TodoDao> todo= todoRepository.findById(id);
		   if(todo.isPresent())
		   {
		 todoRepository.deleteById(id);
		   }
		   else
		   {
			   throw new Exception();
		   }
		 return new ResponseEntity<String>("todo is deleted successfully by Id "+id,HttpStatus.OK);
		   
	} catch (Exception e) {
		// TODO: handle exception
		return new ResponseEntity<String>("todo is not found by Id "+id,HttpStatus.NOT_FOUND);
	}
	}

}
