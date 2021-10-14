package com.app.testoffer.controller;

import java.util.Optional;


import javax.validation.Valid;

import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.testoffer.exception.ResourceException;
import com.app.testoffer.mm.UserMM;
import com.app.testoffer.model.UserEntity;
import com.app.testoffer.service.UserServiceInterface;
/**
 * 
 * @author A697004
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {

	/**
	 * It uses the UserService class to create or display a user,
	 */
	UserServiceInterface userServiceI;
	public UserController(UserServiceInterface userServiceI) {
		super();
		this.userServiceI = userServiceI;
	}
	
	/**
	 * To call save methode to save new user 
	 * @param user
	 * @return
	 * @throws ResourceException exception handling
	 */
	
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserMM user) throws ResourceException {
		try {
			UserEntity _user = userServiceI.createUser(new UserEntity(0, user.getName(), user.getBirthDate(),
					user.getCountryResidence(), user.getPhoneNumber(), user.getGender(), user.getEmail()));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} 	catch (ResourceException e) {
			return new ResponseEntity<String>("Error, the data entered is not consisten", HttpStatus.NOT_ACCEPTABLE);
		}
		catch (ParseException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * To find user by id
	 * @param id 
	 * @return
	 * @throws ResourceException exception handling
	 */
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") long id) throws ResourceException {
		try {
		Optional<UserEntity> userData = userServiceI.findUserById(id);
		return new ResponseEntity<UserEntity>(userData.orElseThrow(), HttpStatus.OK);
		} 
		catch (ResourceException e) {
			return new ResponseEntity<String>("User not found.", HttpStatus.NOT_FOUND);
		}
	}
	


}
