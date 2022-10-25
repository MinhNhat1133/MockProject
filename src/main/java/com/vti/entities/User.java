package com.vti.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vti.enums.Role;
import com.vti.enums.UserStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 3767748140402128107L;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "email")
	private String email;
	
	@Column(name="phone")
	private String phone;

	@Column(name = "password")
	private String password;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role = Role.CUSTOMER;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "`status`", nullable = false)
	private UserStatus status = UserStatus.NOT_ACTIVE;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;

	public User(String email, String password, String fullName ,String phone) {
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.fullName = fullName;
	}

}
