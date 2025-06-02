package com.sekretowicz.workload_service.service;

import com.sekretowicz.workload_service.dto.MonthlyWorkloadDto;
import com.sekretowicz.workload_service.dto.WorkloadRequestDto;
import com.sekretowicz.workload_service.model.TrainerWorkload;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WorkloadService{

    private final Map<String, TrainerWorkload> storage = new ConcurrentHashMap<>();

    public void processWorkload(WorkloadRequestDto dto) {
        TrainerWorkload trainer = storage.computeIfAbsent(dto.getTrainerUsername(), username -> {
            TrainerWorkload t = new TrainerWorkload();
            t.setTrainerUsername(dto.getTrainerUsername());
            t.setFirstName(dto.getFirstName());
            t.setLastName(dto.getLastName());
            t.setActive(dto.getIsActive());
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