package rj.repository;

import java.util.HashMap;
import java.util.Map;

import reactor.core.publisher.Flux;
import rj.model.User;

public class UserRepositorySample implements UserRepository {
	
	
	// initiate Users
	private final Map<Integer, User> users = new HashMap<>();
	
	// fill dummy values for testing
	public UserRepositorySample() {
		this.users.put(100, new User(100, "David"));
		this.users.put(101, new User(101, "John"));
		this.users.put(102, new User(102, "Kevin"));
	}

	@Override
	public Flux<User> getAllUsers() {
		
		//System.out.println();
		
		return Flux.fromIterable(this.users.values());
	}	
	
	@Override
	public Map<Integer, User> getAllUsersMap() {
		
		//System.out.println();
		
		return this.users;
	}
}
