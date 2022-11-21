package com.spring.mongo.exception;

public class TodoExceptionCollection extends Exception 
{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public TodoExceptionCollection(String message)
{
	super(message);
}
	
public static String notFound(String id)
{
	return "todo " +id +" not found";
}
	
public static String todoExsits()
{
	return "todo already exsits with the given name";
}
}
