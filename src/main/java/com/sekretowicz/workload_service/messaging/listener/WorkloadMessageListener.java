package com.sekretowicz.workload_service.messaging.listener;

import com.sekretowicz.workload_service.dto.WorkloadDto;
import com.sekretowicz.workload_service.service.WorkloadDtoValidator;
import com.sekretowicz.workload_service.service.WorkloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WorkloadMessageListener {
    @Autowired
    private WorkloadService workloadService;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private WorkloadDtoValidator validator;

    @JmsListener(destination = "workload.queue")
    public void handle(WorkloadDto dto) {
        if (!validator.isValid(dto)) {
            log.warn("Invalid workload message received: {}", dto);
            throw new IllegalArgumentException("Invalid workload message: " + dto);
        }

        log.info("Received workload message: {}", dto);
        workloadService.processWorkload(dto);
    }
}