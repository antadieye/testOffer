package com.app.testoffer.service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.testoffer.aspect.LogEntryExit;
import com.app.testoffer.exception.ResourceNotFoundException;
import com.app.testoffer.model.UserEntity;
import com.app.testoffer.repository.UserRepository;

@Component
public class UserService implements UserServiceInterface{

	@Autowired
	UserRepository userRepository;
	
	
	// Methode to save new user 
	
	@LogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	@Override
	public UserEntity createUser(UserEntity user) {
		return userRepository.save(user);
	}
	
	// Methode To find user by id
	
	@LogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	@Override
	public Optional<UserEntity> getUserById(Long id) throws ResourceNotFoundException {
		return userRepository.findById(id);
	}
	
}


