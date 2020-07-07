package main.services;

import java.util.Collection;

import main.model.Employee;

public interface Data<T> {

	/** ask the server to add a employee */
	public void add(T element);
	
	/** ask the server to remove a employee */
	public void remove(int num);
	
	/** ask the server to get a employee */
	public Employee get(int num);

	/** ask the server to get the list of all employees */
	public Collection<T> findAll();
}
