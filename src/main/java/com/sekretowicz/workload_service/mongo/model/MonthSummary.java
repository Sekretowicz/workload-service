package com.sekretowicz.workload_service.mongo.model;

import lombok.Data;

@Data
public class MonthSummary {

    private int month;
    private double summaryDuration;

}
