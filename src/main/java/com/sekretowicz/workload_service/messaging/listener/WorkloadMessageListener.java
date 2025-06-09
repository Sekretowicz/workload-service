package com.sekretowicz.workload_service.messaging.listener;

import com.sekretowicz.workload_service.dto.WorkloadDto;
import com.sekretowicz.workload_service.messaging.config.JmsConfig;
import com.sekretowicz.workload_service.service.WorkloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WorkloadMessageListener {
    @Autowired
    private WorkloadService workloadService;

    @JmsListener(destination = JmsConfig.WORKLOAD_QUEUE)
    public void handle(WorkloadDto dto) {
        log.info("Received workload message: {}", dto);
        workloadService.processWorkload(dto);
    }
}