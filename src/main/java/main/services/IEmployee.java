package main.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import feign.Headers;
import feign.RequestLine;
import main.model.Employee;

/** defines what the server controller returns */
public interface IEmployee {

	/** to add to the database */
	@RequestLine("POST")
	@Headers("Content-Type: application/json")
	public void add(@RequestBody Employee product);

	/** to return the list of the database */
	@RequestLine("GET")
	public List<Employee> list();
	
	/** to get from the database */
	@RequestLine("GET")
	public Employee get();
	
	/** to delete from the database */
	@RequestLine("GET")
	public void delete();
}
