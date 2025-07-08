package com.sekretowicz.workload_service.runners;

import com.sekretowicz.workload_service.config.TestConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
classes = TestConfiguration.class)
public class SpringIntegrationTest {
    // executeGet implementation
}
