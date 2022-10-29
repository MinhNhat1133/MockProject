package com.vti.dtos;

import com.vti.entities.Order;
import com.vti.entities.OrderServiceId;
import com.vti.entities.Service;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ServiceCompletionProgressDTO {
    private int orderId;
    private int serviceId;
    private String serviceContent;
    private int serviceWeight;
    private int serviceRequired;
    private LocalDate proposedDate;
    private int status;
    private LocalDate completionDate;

    @Data
    @NoArgsConstructor
    static class ServiceDTO {
        private int serviceId;
        private String serviceContent;
        private int serviceWeight;
        private int required;
    }
}
