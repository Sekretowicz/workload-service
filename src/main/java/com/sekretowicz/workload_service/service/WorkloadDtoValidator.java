package com.sekretowicz.workload_service.service;

import com.sekretowicz.workload_service.messaging.dto.WorkloadDto;
import org.springframework.stereotype.Service;

@Service
public class WorkloadDtoValidator {
    public boolean isValid (WorkloadDto dto) {
        if (dto == null) {
            return false;
        }
        if (dto.getTrainerUsername() == null || dto.getTrainerUsername().isEmpty()) {
            return false;
        }
        if (dto.getFirstName() == null || dto.getFirstName().isEmpty()) {
            return false;
        }
        if (dto.getLastName() == null || dto.getLastName().isEmpty()) {
            return false;
        }
        if (dto.getActive() == null) {
            return false;
        }
        if (dto.getTrainingDate() == null) {
            return false;
        }
        if (dto.getTrainingDuration() == null || dto.getTrainingDuration() <= 0) {
            return false;
        }
        if (dto.getActionType() == null || (!dto.getActionType().equals("ADD") && !dto.getActionType().equals("DELETE"))) {
            return false;
        }
        return true;
    }
}
