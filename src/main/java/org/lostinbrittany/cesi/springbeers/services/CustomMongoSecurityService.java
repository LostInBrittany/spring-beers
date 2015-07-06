package org.lostinbrittany.cesi.springbeers.services;

import org.lostinbrittany.cesi.springbeers.dao.UserRepository;
import org.lostinbrittany.cesi.springbeers.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomMongoSecurityService implements UserDetailsService {

	

	@Autowired
	UserRepository userRep;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		try {

			User user = userRep.findByUsername(username);

			return user;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}



}
