//package com.vti.entities;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.Date;
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "service_completion_progress")
//public class ServiceCompletionProgress implements Serializable {
//    private static final long serialVersionUID = -4785584286934184980L;
//    @EmbeddedId
//    private OrderServicePK id;
//
//    @ManyToOne
//    @MapsId("order_id")
//    @JoinColumn(name = "order_id")
//    private Order order;
//
//    @ManyToOne
//    @MapsId("service_id")
//    @JoinColumn(name = "service_id")
//    private Service service;
//
//    @Column(name = "proposed_date")
//    private LocalDate proposedDate;
//
//
//    private String currentCity;
//
//    @Column(name = "new_city")
//    private String newCity;
//
//    @Column(name = "moving_date")
//    private Date movingDate;
//
//    @ManyToOne
//    @JoinColumn(name="plan_id")
//    private Plan plan;
//
//    @ManyToOne
//    @JoinColumn(name="customer_id")
//    private User customer;
//
//
//    @Column(name = "is_has_apartment_already")
//    private String isHasApartmentAlready;
//
//    @Column(name = "distance")
//    private int distance;
//
//    @Column(name = "payment_status")
//    private String payment_status;
//
//    @Column(name = "payment_details")
//    private String payment_details;
//
//    @Column(name = "payment_date")
//    private Date payment_date;
//
//    @Column(name = "status")
//    private String status;
//
//    @Column(name = "created_date")
//    @Temporal(TemporalType.DATE)
//    @CreationTimestamp
//    private Date created_date;
//}
