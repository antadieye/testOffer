package com.app.testoffer.service;

import java.util.Optional;

import com.app.testoffer.exception.ResourceException;
import com.app.testoffer.model.UserEntity;

/**
 * 
 * @author A697004
 *
 */
public interface UserServiceInterface {
	
	/**
	 * The one that allows to register a user
	 * @param user, the model
	 * @return the method return
	 * @throws ResourceException exception handling
	 */
	UserEntity createUser(UserEntity user) throws ResourceException;
/**

 * One that displays the details of a registered user
 * @param id , id of the user
 * @return, the method return
 * @throws ResourceException, exception handling
 */
	Optional<UserEntity> findUserById(Long id) throws  ResourceException;
}
