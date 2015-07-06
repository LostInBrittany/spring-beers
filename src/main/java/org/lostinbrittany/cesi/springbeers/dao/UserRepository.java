package org.lostinbrittany.cesi.springbeers.dao;
import org.lostinbrittany.cesi.springbeers.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> { 
 
	public abstract User findByUsername(String username);
}
