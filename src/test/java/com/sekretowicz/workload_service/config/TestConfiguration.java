package com.sekretowicz.workload_service.config;

import com.sekretowicz.workload_service.utils.ScenarioContext;
import com.sekretowicz.workload_service.utils.TestDataHelper;
import org.springframework.context.annotation.Bean;

//It's needed because we can't autowire test classes directly in Cucumber steps
@org.springframework.boot.test.context.TestConfiguration
public class TestConfiguration {
    @Bean
    public TestDataHelper testDataHelper() {
        return new TestDataHelper();
    }
    @Bean
    public ScenarioContext scenarioContext() {
        return new ScenarioContext();
    }
}
