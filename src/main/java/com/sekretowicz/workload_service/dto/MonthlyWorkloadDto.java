package com.sekretowicz.workload_service.dto;

import lombok.Data;
import java.util.Map;

@Data
public class MonthlyWorkloadDto {
    private String trainerUsername;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Map<Integer, Map<Integer, Integer>> years; // year -> month -> total duration
}