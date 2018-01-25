package rj.service;


import java.util.Map;

import reactor.core.publisher.Flux;
import rj.model.User;

public interface UserService {

	Flux<User> getAllUsers();	
	
	Map<Integer, User> getAllUsersMap();
}
