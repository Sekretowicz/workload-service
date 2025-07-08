package com.sekretowicz.workload_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthSummary {
    private int month;
    private int summaryDuration;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MonthSummary that = (MonthSummary) obj;

        return (month == that.month && summaryDuration == that.summaryDuration);
    }
}
