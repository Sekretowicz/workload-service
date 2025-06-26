package com.sekretowicz.workload_service.mongo.service;

import com.sekretowicz.workload_service.dto.WorkloadDto;
import com.sekretowicz.workload_service.mongo.dto.TrainingEventDto;
import com.sekretowicz.workload_service.mongo.model.MonthSummary;
import com.sekretowicz.workload_service.mongo.model.TrainerSummary;
import com.sekretowicz.workload_service.mongo.model.YearSummary;
import com.sekretowicz.workload_service.mongo.repo.TrainerSummaryRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

//That's new version of the service that uses MongoDB for storing trainer summaries.
@Service
@RequiredArgsConstructor
public class TrainerSummaryService {

    private static final Logger log = LoggerFactory.getLogger(TrainerSummaryService.class);
    private final TrainerSummaryRepo repo;

    @Transactional
    public void processTrainingEvent(TrainingEventDto dto, String transactionId) {
        log.info("Transaction [{}]: Processing training event for username: {}", transactionId, dto.getUsername());

        TrainerSummary summary = repo.findByUsername(dto.getUsername()).orElseGet(() -> {
            TrainerSummary newSummary = new TrainerSummary();
            newSummary.setUsername(dto.getUsername());
            newSummary.setFirstName(dto.getFirstName());
            newSummary.setLastName(dto.getLastName());
            newSummary.setStatus(dto.isStatus());
            newSummary.setYears(new ArrayList<>());
            return newSummary;
        });

        LocalDate date = dto.getTrainingDate();
        int year = date.getYear();
        int month = date.getMonthValue();

        YearSummary yearSummary = summary.getYears().stream()
                .filter(y -> y.getYear() == year)
                .findFirst()
                .orElseGet(() -> {
                    YearSummary y = new YearSummary();
                    y.setYear(year);
                    y.setMonths(new ArrayList<>());
                    summary.getYears().add(y);
                    return y;
                });

        MonthSummary monthSummary = yearSummary.getMonths().stream()
                .filter(m -> m.getMonth() == month)
                .findFirst()
                .orElseGet(() -> {
                    MonthSummary m = new MonthSummary();
                    m.setMonth(month);
                    m.setSummaryDuration(0);
                    yearSummary.getMonths().add(m);
                    return m;
                });

        double updatedDuration = monthSummary.getSummaryDuration() + dto.getTrainingDuration();
        monthSummary.setSummaryDuration(updatedDuration);

        repo.save(summary);
        log.info("Transaction [{}]: Summary updated and saved.", transactionId);
    }

    //Overloaded method to accept WorkloadDto directly
    @Transactional
    public void processTrainingEvent(WorkloadDto dto, String transactionId) {
        processTrainingEvent(new TrainingEventDto(dto), transactionId);
    }
}
