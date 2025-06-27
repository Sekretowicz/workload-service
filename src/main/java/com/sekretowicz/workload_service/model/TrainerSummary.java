package com.sekretowicz.workload_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "trainer_summaries")
@Data
public class TrainerSummary {
    @Id
    private String id;
    private String username;

    @TextIndexed
    private String firstName;

    @TextIndexed
    private String lastName;

    private boolean status;
    private List<YearSummary> years;
}
