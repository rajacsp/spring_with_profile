package rj.repository;

import java.util.Map;

import reactor.core.publisher.Flux;
import rj.model.User;

public interface UserRepository {

	Flux<User> getAllUsers();
	
	Map<Integer, User> getAllUsersMap();
}
