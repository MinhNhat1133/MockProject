package com.vti.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="services")
public class Service implements Serializable{
	private static final long serialVersionUID = 1974879449261634037L;
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "service_content")
	private String serviceContent;

	@Column(name = "service_weight")
	private int serviceWeight;

	@Column(name = "service_time")
	private int serviceTime;
	
	@Column(name = "required")
	private int required;
}
