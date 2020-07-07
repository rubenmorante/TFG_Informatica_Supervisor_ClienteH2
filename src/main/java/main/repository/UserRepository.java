package main.repository;

import org.springframework.data.repository.CrudRepository;

import main.model.User;

public interface UserRepository  extends CrudRepository<User, Long>{
	
	/** Find by user */
	User findByUser(String user);

}
