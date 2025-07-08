package com.sekretowicz.workload_service.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//Used for messaging between services
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkloadDto {
    private String trainerUsername;
    private String firstName;
    private String lastName;
    //Renamed to active, because Jackson will serialize it as "active" instead of "isActive"
    private Boolean active;
    private LocalDate trainingDate;
    private Integer trainingDuration;
    private String actionType; // "ADD" or "DELETE"

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        WorkloadDto that = (WorkloadDto) obj;
        return trainerUsername.equals(that.trainerUsername) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                active.equals(that.active) &&
                trainingDate.equals(that.trainingDate) &&
                trainingDuration.equals(that.trainingDuration) &&
                actionType.equals(that.actionType);
    }
}