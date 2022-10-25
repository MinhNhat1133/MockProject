package com.vti.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.entities.User;
import com.vti.forms.CustomerAndOrderCreateForm;

public interface UserService extends UserDetailsService {

	void createUser(User entity);

	void sendConfirmUserRegistrationViaEmail(String email);

	User findUserByUserName(String name);

	User findUserByEmail(String email);

	void activeUser(String token);

	ResponseEntity<Object> createUAO( CustomerAndOrderCreateForm createCustomerAndOrderForm);

	User findUserByEmail4LogIn(String name);

	User findUserByEmailNotActive(String email);

}
