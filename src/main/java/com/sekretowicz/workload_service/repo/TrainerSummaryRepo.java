package com.sekretowicz.workload_service.repo;

import com.sekretowicz.workload_service.model.TrainerSummary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TrainerSummaryRepo extends MongoRepository<TrainerSummary, String> {
    Optional<TrainerSummary> findByUsername(String username);
}
