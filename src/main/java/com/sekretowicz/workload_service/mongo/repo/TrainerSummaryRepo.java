package com.sekretowicz.workload_service.mongo.repo;

import com.sekretowicz.workload_service.mongo.model.TrainerSummary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TrainerSummaryRepo extends MongoRepository<TrainerSummary, String> {
    Optional<TrainerSummary> findByUsername(String username);
}
