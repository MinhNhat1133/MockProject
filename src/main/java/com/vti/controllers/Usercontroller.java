package com.vti.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.forms.CustomerAndOrderCreateForm;
import com.vti.services.UserService;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/v1/users")
@Validated
public class Usercontroller {
	@Autowired
	private UserService userService;

	@PostMapping("/createUAO")
	public ResponseEntity<Object> createUserAndOrder( @RequestBody @Valid CustomerAndOrderCreateForm createCustomerAndOrderForm) {
		// create User 
//		userService.createUser(dto.toEntity());
		return this.userService.createUAO(createCustomerAndOrderForm);
//		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
	}
	
//	@GetMapping("/activeUser")
//	// validate: check exists, check not expired
//	public ResponseEntity<?> activeUserViaEmail(@RequestParam String token) {
//		// active user
//		userService.activeUser(token);
//
//		return new ResponseEntity<>("Active success!", HttpStatus.OK);
//	}

	@GetMapping("/activeUser")
	// validate: check exists, check not expired
	public RedirectView activeUserViaEmail(@RequestParam String token) {
		// active user
		userService.activeUser(token);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("https://fullstackdeveloper.guru/");

		return redirectView;
	}
}
