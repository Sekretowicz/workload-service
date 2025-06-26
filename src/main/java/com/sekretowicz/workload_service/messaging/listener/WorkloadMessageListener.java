package com.sekretowicz.workload_service.messaging.listener;

import com.sekretowicz.workload_service.dto.WorkloadDto;
import com.sekretowicz.workload_service.messaging.config.JmsConfig;
import com.sekretowicz.workload_service.mongo.service.TrainerSummaryService;
import com.sekretowicz.workload_service.service.WorkloadDtoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class WorkloadMessageListener {

    private final TrainerSummaryService trainerSummaryService;
    private final WorkloadDtoValidator validator;

    @JmsListener(destination = JmsConfig.WORKLOAD_QUEUE)
    public void handle(WorkloadDto dto) {
        String transactionId = UUID.randomUUID().toString();

        if (!validator.isValid(dto)) {
            log.warn("Transaction [{}]: Invalid workload message received: {}", transactionId, dto);
            throw new IllegalArgumentException("Invalid workload message: " + dto);
        }

        log.info("Transaction [{}]: Received workload message: {}", transactionId, dto);

        trainerSummaryService.processTrainingEvent(dto, transactionId);
    }
}