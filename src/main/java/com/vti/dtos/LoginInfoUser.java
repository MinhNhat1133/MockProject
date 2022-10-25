package com.vti.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginInfoUser {

	private String token;


	private String email;
	private String phone;

	private String fullName;

	private String role;

	private String status;



	public LoginInfoUser(String token, String email, String fullName, String role, String phone,
			String status) {
		this.token = token;
		this.email = email;
		this.phone = phone;
		this.fullName = fullName;
		this.role = role;
		this.status = status;
	}
}
