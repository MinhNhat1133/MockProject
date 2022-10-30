package com.vti.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable{
	private static final long serialVersionUID = -4785584286934184980L;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "current_city")
	private String currentCity;

	@Column(name = "new_city")
	private String newCity;
	
	@Column(name = "moving_date")
	private Date movingDate;

	@ManyToOne
	@JoinColumn(name="plan_id")
	private Plan plan;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private User customer;
	
	
	@Column(name = "is_has_apartment_already")
	private String isHasApartmentAlready;
	
	@Column(name = "distance")
	private int distance;
	
	@Column(name = "payment_status", columnDefinition = "default 0")
	private int payment_status;
	
	@Column(name = "payment_details")
	private String payment_details;
	
	@Column(name = "payment_date")
	private Date payment_date;
	
	@Column(name = "status", columnDefinition = "default 0")
	private int status;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private Date created_date;
	
	
}
