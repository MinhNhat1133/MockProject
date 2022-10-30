package com.vti.dtos;

import com.vti.entities.Plan;
import com.vti.entities.User;
import com.vti.enums.Role;
import com.vti.enums.UserStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class OrderDTO {

    private int id;

    private String currentCity;

    private String newCity;

    private LocalDate movingDate;

    private PlanDTO plan;

    private UserDTO customer;

    private int isHasApartmentAlready;

    private int distance;

    private int payment_status;

    private String payment_details;

    private LocalDate payment_date;

    private String status;

    private LocalDate created_date;

    @Data
    @NoArgsConstructor
    static class UserDTO {
        private int id;
        private String email;
        private String phone;
        private String fullName;
        private Role role = Role.CUSTOMER;
        private UserStatus status = UserStatus.NOT_ACTIVE;

    }

    @Data
    @NoArgsConstructor
    static class PlanDTO {
        private int id;

        private String planName;

        private String price;

    }


}
