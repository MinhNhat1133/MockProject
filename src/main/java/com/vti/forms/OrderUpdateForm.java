package com.vti.forms;

import com.vti.entities.Plan;
import com.vti.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
public class OrderUpdateForm {
    private String currentCity;

    private String newCity;

    private Date movingDate;

    private String isHasApartmentAlready;

    private int distance;

    private Integer paymentStatus;

    private String payment_details;

    private Date payment_date;

    private int status;
}
