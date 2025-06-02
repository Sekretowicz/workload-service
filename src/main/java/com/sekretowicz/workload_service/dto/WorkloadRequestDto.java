package com.sekretowicz.workload_service.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class WorkloadRequestDto {
    private String trainerUsername;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private LocalDate trainingDate;
    private Integer trainingDuration;
    private String actionType; // "ADD" or "DELETE"
}