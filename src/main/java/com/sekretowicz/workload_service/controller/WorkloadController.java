package com.sekretowicz.workload_service.controller;

import com.sekretowicz.workload_service.messaging.dto.WorkloadDto;
import com.sekretowicz.workload_service.service.TrainerSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/workload")
public class WorkloadController {

    @Autowired
    private TrainerSummaryService service;

    //Let's just check if we can add a new document directly to the database
    //It's just adding some mock John Doe data for testing purposes
    @PostMapping
    public ResponseEntity<Void> add() {
        WorkloadDto dto = new WorkloadDto();
        dto.setTrainerUsername("john_doe");
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setActive(true);
        dto.setTrainingDate(LocalDate.now());
        dto.setTrainingDuration(60);
        dto.setActionType("ADD");

        service.processTrainingEvent(dto, "nothing");

        return ResponseEntity.ok().build();
    }
}