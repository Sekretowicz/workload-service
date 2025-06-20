package com.sekretowicz.workload_service.messaging.listener;

import com.sekretowicz.workload_service.dto.WorkloadDto;
import com.sekretowicz.workload_service.messaging.config.JmsConfig;
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

    @JmsListener(destination = "workload.queue")
    public void handle(WorkloadDto dto) {
        //Validate the message. If invalid, throw an exception
        if (dto.getTrainerUsername() == null || dto.getTrainerUsername().isEmpty()) {
            log.error("Received workload message with empty trainerUsername: {}", dto);
            jmsTemplate.convertAndSend("ActiveMQ.DLQ", dto);
            return;
        }
        if (dto.getFirstName() == null || dto.getFirstName().isEmpty()) {
            log.error("Received workload message with empty firstName: {}", dto);
            jmsTemplate.convertAndSend("ActiveMQ.DLQ", dto);
            return;
        }
        if (dto.getLastName() == null || dto.getLastName().isEmpty()) {
            log.error("Received workload message with empty lastName: {}", dto);
            jmsTemplate.convertAndSend("ActiveMQ.DLQ", dto);
            return;
        }
        if (!dto.getActive()) {
            log.error("Received workload message with inactive status: {}", dto);
            jmsTemplate.convertAndSend("ActiveMQ.DLQ", dto);
            return;
        }

        log.info("Received workload message: {}", dto);
        workloadService.processWorkload(dto);
    }
}