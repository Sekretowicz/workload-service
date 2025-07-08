package com.sekretowicz.workload_service.utils;

import com.mongodb.client.MongoDatabase;
import com.sekretowicz.workload_service.messaging.config.JmsConfig;
import com.sekretowicz.workload_service.messaging.dto.WorkloadDto;
import com.sekretowicz.workload_service.model.MonthSummary;
import com.sekretowicz.workload_service.model.TrainerSummary;
import com.sekretowicz.workload_service.model.YearSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.time.LocalDate;
import java.util.List;

public class TestDataHelper {
    @Autowired
    private MongoTemplate mongoTemplate;

    //It may be a bit tricky, but this class is used to create expected data for tests.

    public TrainerSummary getExpectedTrainerSummary(WorkloadDto dto) {
        //Expected month workload, we take it from the dto
        //We assume that trainer has no previous data, so we create a new summary
        MonthSummary monthSummary = new MonthSummary(
                dto.getTrainingDate().getMonthValue(),
                dto.getTrainingDuration()
        );
        //Then we create a year summary with this month
        YearSummary yearSummary = new YearSummary(
                dto.getTrainingDate().getYear(),
                List.of(monthSummary)
        );
        //Finally, we create a trainer summary with this year
        return new TrainerSummary(
                null,                //FIXME: How id is ever generated?
                dto.getTrainerUsername(),
                dto.getFirstName(),
                dto.getLastName(),
                true,
                List.of(yearSummary));
    }

    //Hardcoded by now, but can be extended to create more complex test data
    public WorkloadDto getWorkloadDto() {
        return new WorkloadDto(
                "johndoe123",
                "John",
                "Doe",
                true,
                LocalDate.now(),
                60,
                "ADD");
    }

    public void clearTestEnvironment() {
        //Clear MongoDB
        MongoDatabase db = mongoTemplate.getDb();
        db.drop();
    }

}
