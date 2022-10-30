package com.vti.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "plans")
@Data
public class Plan  implements Serializable{
	private static final long serialVersionUID = -4248211684582510275L;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "plan_name")
	private String planName;

	@Column(name = "price")
	private String price;
	
	@OneToMany(mappedBy = "plan")
	private List<Order> orders;

	
}
