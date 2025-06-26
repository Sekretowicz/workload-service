package com.sekretowicz.workload_service.controller;

import com.sekretowicz.workload_service.mongo.dto.TrainingEventDto;
import com.sekretowicz.workload_service.mongo.service.TrainerSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/workload")
public class WorkloadController {

    @Autowired
    private TrainerSummaryService service;

    //It's not used in current task, because now we use ActiveMQ for communication
    //Let's just check if we can add a new document directly to the database
    @PostMapping
    public ResponseEntity<Void> add() {
        TrainingEventDto dto = new TrainingEventDto();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setUsername("johndoe");
        dto.setTrainingDate(LocalDate.now());
        dto.setTrainingDuration(60);
        service.processTrainingEvent(dto, "test-transaction-id");

        return ResponseEntity.ok().build();
    }

    /*
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
     */
}