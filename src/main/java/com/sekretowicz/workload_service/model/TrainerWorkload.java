package com.sekretowicz.workload_service.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
public class TrainerWorkload {
    private String trainerUsername;
    private String firstName;
    private String lastName;
    private boolean isActive;

    private final Map<Integer, Map<Integer, Integer>> years = new HashMap<>();

    public void addDuration(LocalDate date, int duration) {
        int year = date.getYear();
        int month = date.getMonthValue();

        years
                .computeIfAbsent(year, y -> new HashMap<>())
                .merge(month, duration, Integer::sum);
    }

    public void removeDuration(LocalDate date, int duration) {
        int year = date.getYear();
        int month = date.getMonthValue();

        Map<Integer, Integer> months = years.get(year);
        if (months == null) return;

        int updated = months.getOrDefault(month, 0) - duration;
        if (updated > 0) {
            months.put(month, updated);
        } else {
            months.remove(month);
            if (months.isEmpty()) {
                years.remove(year);
            }
        }
    }
}