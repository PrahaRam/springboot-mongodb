package com.spring.mongo.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection ="Todos")
public class TodoDao {
    
	@Id
	private String id;
	
	@NotNull(message = "todo can not be null")
	private String todo;
	
	@NotNull(message = "desc can not be null")
	private String description;
	
	@NotNull(message = "completed can not be null")
	private String completed;
	
	
	private Date createdAt;
	  
	private Date updatedAt;
}
