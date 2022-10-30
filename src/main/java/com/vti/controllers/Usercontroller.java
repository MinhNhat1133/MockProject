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

<<<<<<< Updated upstream
=======
import com.vti.dtos.ServiceCompletionProgressDTO;
import com.vti.dtos.UserDTO;
import com.vti.entities.Service;
import com.vti.entities.User;
import com.vti.forms.CreatingFormService;
import com.vti.forms.CreatingFormUser;
>>>>>>> Stashed changes
import com.vti.forms.CustomerAndOrderCreateForm;
import com.vti.services.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/v1")
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
	
	@GetMapping("/activeUser")
	// validate: check exists, check not expired
	public ResponseEntity<?> activeUserViaEmail(@RequestParam String token) {
		// active user
		userService.activeUser(token);

<<<<<<< Updated upstream
		return new ResponseEntity<>("Active success!", HttpStatus.OK);
=======
		return redirectView;
	}
	
//	User
	@GetMapping("/users")
	public List<UserDTO> getListUser() {
		List<User> users = userService.getListUser();
		List<UserDTO> listUserDTO = modelMapper.map(users, new TypeToken<List<UserDTO>> () {}.getType());
		return listUserDTO;
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> creatingUser(@RequestBody @Valid CreatingFormUser cFU) {
		User user = modelMapper.map(cFU, User.class);
		userService.creatingUser(user);
		return ResponseEntity.ok().body("Created user successfully!");
	}
	
	@PutMapping("/users")
	public ResponseEntity<?> updateUser(@RequestParam(name = "id") int id, @RequestBody @Valid CreatingFormUser cFU) {
		User user = modelMapper.map(cFU, User.class);
		user.setId(id);
		userService.updateUser(user);
		return ResponseEntity.ok().body("Updated user successfully!");
	}
	
	@DeleteMapping("/users")
	public ResponseEntity<?> deleteUser(@RequestParam(name = "id") int id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().body("User Deleted");
	}
	
	@DeleteMapping(value = "/users/{ids}")
	public ResponseEntity<?> deleteUsers(@PathVariable(name = "ids") List<Short> ids) {
		userService.deleteUsers(ids);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
>>>>>>> Stashed changes
	}
	
//	Service
	@GetMapping("/service")
	public List<ServiceCompletionProgressDTO> getListService() {
		List<org.springframework.stereotype.Service> service = userService.getListService();
		List<ServiceCompletionProgressDTO> lSCPDTO = modelMapper.map(service, new TypeToken<List<ServiceCompletionProgressDTO>> () {}.getType());
		return lSCPDTO;
	}
	
	@PostMapping("/service")
	public ResponseEntity<?> creatingService(@RequestBody @Valid CreatingFormService CFS) {
		Service service = modelMapper.map(CFS, Service.class);
		userService.creatingService(service);
		return ResponseEntity.ok().body("Created service successfully!");
	}
	
	@PutMapping("/service")
	public ResponseEntity<?> updateService(@RequestParam(name = "id") int id, @RequestBody @Valid CreatingFormService CFS) {
		Service service = modelMapper.map(CFS, Service.class);
		service.setId(id);
		userService.updateService(service);
		return ResponseEntity.ok().body("Updated service successfully!");
	}
	
	@DeleteMapping("/service")
	public ResponseEntity<?> deleteService(@RequestParam(name = "id") int id) {
		userService.deleteService(id);
		return ResponseEntity.ok().body("Service Deleted");
	}
}
