package com.sekretowicz.workload_service.model;

import lombok.Data;

import java.util.List;

@Data
public class YearSummary {
    private int year;
    private List<MonthSummary> months;
}
