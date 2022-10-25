package com.vti.dtos;

public class ProfileDTO {

	private String userName;

	private String email;

	private String firstName;

	private String lastName;

	private String role;

	private String status;

	private String avatarUrl;

	public ProfileDTO(String userName, String email, String firstName, String lastName, String role, String status,
			String avatarUrl) {
		this.userName = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.status = status;
		this.avatarUrl = avatarUrl;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getRole() {
		return role;
	}

	public String getStatus() {
		return status;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

}
