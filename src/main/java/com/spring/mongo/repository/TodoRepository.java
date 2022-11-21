package com.spring.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.mongo.model.TodoDao;

@Repository
public interface TodoRepository extends MongoRepository<TodoDao,String> {
	
	@Query("{'todo':?0}")
	Optional<TodoDao> findByTodo(String todo);

}
