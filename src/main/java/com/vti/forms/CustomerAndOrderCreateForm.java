package com.vti.forms;

import java.time.LocalDate;
import java.util.Date;

import com.vti.enums.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerAndOrderCreateForm {
	private String email;
	private String phone;
	private String password;
	private String fullName;
	private Role role;
	private String currentCity;
	private String newCity;
	private LocalDate movingDate;
	private int planId;
	private int isHasApartmentAlready;
	private int distance;
}
