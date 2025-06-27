package com.sekretowicz.workload_service.messaging.dto;

import lombok.Data;
import java.time.LocalDate;

//Used for messaging between services
@Data
public class WorkloadDto {
    private String trainerUsername;
    private String firstName;
    private String lastName;
    //Renamed to active, because Jackson will serialize it as "active" instead of "isActive"
    private Boolean active;
    private LocalDate trainingDate;
    private Integer trainingDuration;
    private String actionType; // "ADD" or "DELETE"
}