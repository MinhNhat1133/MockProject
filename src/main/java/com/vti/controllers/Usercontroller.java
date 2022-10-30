package com.vti.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dtos.UserDTO;
import com.vti.entities.User;
import com.vti.forms.CrestingFormUser;
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
	
	@Autowired
	private ModelMapper modelMapper;

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
		redirectView.setUrl("http://localhost:3000/sign-in");

		return redirectView;
	}
	
	@GetMapping()
	public List<UserDTO> getListUser() {
		List<User> users = userService.getListUser();
		List<UserDTO> listUserDTO = modelMapper.map(users, new TypeToken<List<UserDTO>> () {}.getType());
		return listUserDTO;
	}
	
	@PostMapping()
	public ResponseEntity<?> creatingUser(@RequestBody @Valid CrestingFormUser cFU) {
		User user = modelMapper.map(cFU, User.class);
		userService.creatingUser(user);
		return ResponseEntity.ok("Created user successfully!");
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestParam(name = "id") int id, @RequestBody @Valid CrestingFormUser cFU) {
		User user = modelMapper.map(cFU, User.class);
		user.setId(id);
		userService.updateUser(user);
		return ResponseEntity.ok().body("Updated user successfully!");
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteUser(@RequestParam(name = "id") int id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().body("User Deleted");
	}
	
	@DeleteMapping(value = "/{ids}")
	public ResponseEntity<?> deleteUsers(@PathVariable(name = "ids") List<Short> ids) {
		userService.deleteUsers(ids);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
}
