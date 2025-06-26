package com.sekretowicz.workload_service.service;

import com.sekretowicz.workload_service.dto.MonthlyWorkloadDto;
import com.sekretowicz.workload_service.dto.WorkloadDto;
import com.sekretowicz.workload_service.model.TrainerWorkload;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

/* This service is responsible for managing the workload of trainers.
 * It processes incoming workload data and provides monthly workload summaries.
 *
 * @deprecated Use {@link com.sekretowicz.workload_service.mongo.service.TrainerSummaryService} instead.
 */
@Service
@Deprecated
public class WorkloadService{

    @Getter
    private final Map<String, TrainerWorkload> storage = new ConcurrentHashMap<>();

    public void processWorkload(WorkloadDto dto) {
        TrainerWorkload trainer = storage.computeIfAbsent(dto.getTrainerUsername(), username -> {
            TrainerWorkload t = new TrainerWorkload();
            t.setTrainerUsername(dto.getTrainerUsername());
            t.setFirstName(dto.getFirstName());
            t.setLastName(dto.getLastName());
            t.setActive(dto.getActive());
            return t;
        });

        switch (dto.getActionType()) {
            case "ADD" -> trainer.addDuration(dto.getTrainingDate(), dto.getTrainingDuration());
            case "DELETE" -> trainer.removeDuration(dto.getTrainingDate(), dto.getTrainingDuration());
            default -> throw new IllegalArgumentException("Unknown action type: " + dto.getActionType());
        }
    }

    public MonthlyWorkloadDto getMonthlyWorkload(String trainerUsername) {
        TrainerWorkload trainer = storage.get(trainerUsername);
        if (trainer == null) {
            throw new NoSuchElementException("Trainer not found: " + trainerUsername);
        }

        MonthlyWorkloadDto dto = new MonthlyWorkloadDto();
        dto.setTrainerUsername(trainer.getTrainerUsername());
        dto.setFirstName(trainer.getFirstName());
        dto.setLastName(trainer.getLastName());
        dto.setIsActive(trainer.isActive());
        dto.setYears(trainer.getYears());

        return dto;
    }

}