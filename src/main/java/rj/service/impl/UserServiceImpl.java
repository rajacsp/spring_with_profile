package rj.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import rj.model.User;
import rj.repository.UserRepository;
import rj.repository.UserRepositorySample;
import rj.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;	
	
	public UserServiceImpl(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	public UserServiceImpl(){
		this.userRepository = new UserRepositorySample();
	}
	
	public Flux<User> getAllUsers(){
		Flux<User> users = this.userRepository.getAllUsers();		
		System.out.println("users : "+users);
		
		return users;
	}
	
	public Map<Integer, User> getAllUsersMap(){
		Map<Integer, User> users = this.userRepository.getAllUsersMap();	
		System.out.println("users : "+users);
		
		return users;
	}
}
