package com.app.testoffer.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.app.testoffer.exception.ResourceException;
import com.app.testoffer.model.UserEntity;
import com.app.testoffer.repository.UserRepository;

@Component
/**
 * 
 * @author A697004
 *
 */
public class UserService implements UserServiceInterface{

	/**
	 * calls the UserRepository class which allows it to store and display the details of a user.
	 */
	UserRepository userRepository;
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	
	/**
	 *  Methode to save new user 
	 */
	@Override
	public UserEntity createUser(UserEntity user) throws ResourceException {
		Period diff = Period.between(LocalDate.parse(user.getBirthDate().toString()), LocalDate.now());
		if (user.getCountryResidence().equalsIgnoreCase("France") && diff.getYears() >= 18) {
			return userRepository.save(user);
		}
		else {
				throw new ResourceException("Inconsistency! Please check the data entered.");
		}

	}
	
	/**
	 * Methode To find user by id
	 */
	@Override
	public Optional<UserEntity> findUserById(Long id) throws ResourceException {
		Optional<UserEntity> userData = userRepository.findById(id);
		if (userData.isPresent()) {
			return userRepository.findById(id);
		} else {
			throw new ResourceException("User not found.");
			
		}
	}
	
}


