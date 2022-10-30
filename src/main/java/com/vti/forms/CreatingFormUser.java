package com.vti.forms;

import javax.validation.constraints.NotBlank;

import com.vti.enums.Role;
import com.vti.enums.UserStatus;

import lombok.Data;

@Data
public class CreatingFormUser {
	
	@NotBlank(message = "The email cannot be blank")
	private String email;
	
	@NotBlank(message = "The phone cannot be blank")
	private String phone;
	
	@NotBlank(message = "The password cannot be blank")
	private String password;
	
	@NotBlank(message = "The fullName cannot be blank")
	private String fullName;
	
	private Role role = Role.CUSTOMER;
	
	private UserStatus status = UserStatus.NOT_ACTIVE;
}
