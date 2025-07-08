package com.sekretowicz.workload_service.utils;

import com.sekretowicz.workload_service.messaging.dto.WorkloadDto;
import com.sekretowicz.workload_service.model.TrainerSummary;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ScenarioContext {
    @Autowired
    private TestDataHelper testDataHelper;

    private WorkloadDto expectedMessage = null;
    private TrainerSummary expectedSummary = null;

    @Getter
    @Setter
    private WorkloadDto messageFromMQ = null;

    public WorkloadDto getExpectedMessage() {
        if (expectedMessage == null) {
            expectedMessage = testDataHelper.getWorkloadDto();
        }
        return expectedMessage;
    }

    public TrainerSummary getExpectedSummary() {
        if (expectedSummary == null) {
            expectedSummary = testDataHelper.getExpectedTrainerSummary(getExpectedMessage());
        }
        return expectedSummary;
    }
}
