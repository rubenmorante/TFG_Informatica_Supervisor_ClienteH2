package main.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import main.model.Employee;

@Service
public class DataService implements Data<Employee>{

	/** Override ask the server to add a product */
	@Override
	public void add(Employee employee) {
		IEmployee iEmployee = Feign.builder()
			.client(new OkHttpClient())
			.encoder(new JacksonEncoder())
			.decoder(new JacksonDecoder())
			.target(IEmployee.class, "http://localhost:8080/add");
		iEmployee.add(employee);		
	}

	/** Override ask the server to remove a product */
	@Override
	public void remove(int num) {
		IEmployee iEmployee = Feign.builder()
				.client(new OkHttpClient())
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.target(IEmployee.class, "http://localhost:8080/delete/" + num);
		iEmployee.delete();		
	}

	/** Override ask the server to get a product */
	@Override
	public Employee get(int num) {
		IEmployee iEmployee = Feign.builder()
				.client(new OkHttpClient())
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.target(IEmployee.class, "http://localhost:8080/show/" + num);
		return iEmployee.get();	
	}

	/** Override ask the server to get the list of all products */
	@Override
	public Collection<Employee> findAll() {
		IEmployee iEmployee = Feign.builder()
				.client(new OkHttpClient())
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.target(IEmployee.class, "http://localhost:8080/list");
		return iEmployee.list();
	}

}
