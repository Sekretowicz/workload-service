package com.sekretowicz.workload_service.controller;

import com.sekretowicz.workload_service.dto.MonthlyWorkloadDto;
import com.sekretowicz.workload_service.dto.WorkloadDto;
import com.sekretowicz.workload_service.model.TrainerWorkload;
import com.sekretowicz.workload_service.service.WorkloadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/workload")
public class WorkloadController {

    private final WorkloadService service;

    public WorkloadController(WorkloadService service) {
        this.service = service;
    }

    //It's not used in current task, because now we use ActiveMQ for communication
    @PostMapping
    public ResponseEntity<Void> handle(@RequestBody WorkloadDto dto) {
        service.processWorkload(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<MonthlyWorkloadDto> get(@PathVariable String username) {
        MonthlyWorkloadDto response = service.getMonthlyWorkload(username);
        return ResponseEntity.ok(response);
    }

    //Get all trainers
    @GetMapping
    public ResponseEntity<Map<String,TrainerWorkload>> getAll() {
        Map<String, TrainerWorkload> response = service.getStorage();
        return ResponseEntity.ok(response);
    }
}