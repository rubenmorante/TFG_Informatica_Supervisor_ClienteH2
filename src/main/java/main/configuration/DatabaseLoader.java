package main.configuration;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import main.model.User;
import main.repository.UserRepository;

@Component
public class DatabaseLoader {

	@Autowired
	private UserRepository userRepository;
	
	/** initializes users */ 
	@PostConstruct
	private void initDatabase(){
		GrantedAuthority[] userRoles = {
				new SimpleGrantedAuthority("ROLE_USER")
		};
		this.userRepository.save(new User("user", "user", Arrays.asList(userRoles)));
		
		GrantedAuthority[] adminRoles = {
				new SimpleGrantedAuthority("ROLE_ADMIN")
		};
		this.userRepository.save(new User("admin", "admin", Arrays.asList(adminRoles)));
	}
}
