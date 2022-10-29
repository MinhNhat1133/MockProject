package com.vti.forms;

import javax.validation.constraints.NotBlank;

import com.vti.enums.Role;
import com.vti.enums.UserStatus;

import lombok.Data;

@Data
public class CrestingFormUser {
	
	@NotBlank(message = "The email cannot be blank")
	private String email;
	
	@NotBlank(message = "The email cannot be blank")
	private String phone;
	
	@NotBlank(message = "The email cannot be blank")
	private String password;
	
	@NotBlank(message = "The email cannot be blank")
	private String fullName;
	
	private Role role = Role.CUSTOMER;
	
	private UserStatus status = UserStatus.NOT_ACTIVE;
}
