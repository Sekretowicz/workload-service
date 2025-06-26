package com.sekretowicz.workload_service.mongo.dto;

import com.sekretowicz.workload_service.dto.WorkloadDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TrainingEventDto {

    private String username;
    private String firstName;
    private String lastName;
    private boolean status;
    private LocalDate trainingDate;
    private double trainingDuration;

    //Mapping constructor
    public TrainingEventDto (WorkloadDto dto) {
        this.username = dto.getTrainerUsername();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.status = dto.getActive();
        this.trainingDate = dto.getTrainingDate();
        this.trainingDuration = dto.getTrainingDuration();
    }
}
