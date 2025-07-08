package com.sekretowicz.workload_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YearSummary {
    private int year;
    private List<MonthSummary> months;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        YearSummary that = (YearSummary) obj;

        if (year != that.year) return false;
        return months.equals(that.months);
    }
}
