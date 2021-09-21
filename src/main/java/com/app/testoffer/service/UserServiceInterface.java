package com.app.testoffer.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.testoffer.exception.ResourceNotFoundException;
import com.app.testoffer.model.UserEntity;

@Service
public interface UserServiceInterface {
	
	UserEntity createUser(UserEntity user);

	Optional<UserEntity> getUserById(Long id) throws ResourceNotFoundException;
}
