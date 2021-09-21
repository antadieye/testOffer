package com.app.testoffer.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.testoffer.exception.ResourceBadRequestException;
import com.app.testoffer.exception.ResourceNotFoundException;
import com.app.testoffer.model.UserEntity;
import com.app.testoffer.service.UserServiceInterface;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserServiceInterface userServiceI;

	// To call save methode to save new user 
	@PostMapping("/saveUser")
	public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user) throws ResourceBadRequestException {
		try {
			Period diff = Period.between(LocalDate.parse(user.getBirthDate().toString()), LocalDate.now());
			if (user.getCountryResidence().equalsIgnoreCase("France") && diff.getYears() >= 18) {
				UserEntity _user = userServiceI.createUser(new UserEntity(0, user.getName(), user.getBirthDate(),
						user.getCountryResidence(), user.getPhoneNumber(), user.getGender(), user.getEmail()));
				return new ResponseEntity<>(_user, HttpStatus.CREATED);
			}
			else {
					throw new ResourceBadRequestException("Error, the data entered is not consistent.");
			}
	
		} catch (ParseException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// To find user by id
	@GetMapping("/getUser/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable("id") long id) throws ResourceNotFoundException {
		Optional<UserEntity> userData = userServiceI.getUserById(id);
		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("User not found.");
		}
	}
}
