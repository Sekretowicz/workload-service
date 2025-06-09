package com.sekretowicz.workload_service.dto;

import lombok.Data;
import java.time.LocalDate;

//Renamed from WorkloadRequestDto to WorkloadDto - now used both for REST and JMS
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