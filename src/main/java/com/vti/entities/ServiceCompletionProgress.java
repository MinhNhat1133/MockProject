package com.vti.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "service_completion_progress")
public class ServiceCompletionProgress implements Serializable {
    private static final long serialVersionUID = -4785584286934184980L;
    @EmbeddedId
    private OrderServiceId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("serviceId")
    @JoinColumn(name = "service_id")
    private Service service;

    @Column(name = "proposed_date")
    private LocalDate proposedDate;

    @Column(name = "status")
    private int status;

    @Column(name = "completion_date")
    private LocalDate completionDate;

}
