package com.sekretowicz.workload_service.controller;

import com.sekretowicz.workload_service.dto.MonthlyWorkloadDto;
import com.sekretowicz.workload_service.dto.WorkloadRequestDto;
import com.sekretowicz.workload_service.service.WorkloadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workload")
public class WorkloadController {

    private final WorkloadService service;

    public WorkloadController(WorkloadService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> handle(@RequestBody WorkloadRequestDto dto) {
        service.processWorkload(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<MonthlyWorkloadDto> get(@PathVariable String username) {
        MonthlyWorkloadDto response = service.getMonthlyWorkload(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello")
    public void sayHello() {
        System.out.println("Hello from Workload Service!");
    }
}