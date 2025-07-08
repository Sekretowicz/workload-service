package com.sekretowicz.workload_service.steps;

import com.sekretowicz.workload_service.messaging.config.JmsConfig;
import com.sekretowicz.workload_service.model.TrainerSummary;
import com.sekretowicz.workload_service.repo.TrainerSummaryRepo;
import com.sekretowicz.workload_service.utils.ScenarioContext;
import com.sekretowicz.workload_service.utils.TestDataHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class ActiveMQSteps {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ScenarioContext scenarioContext;
    @Autowired
    private TrainerSummaryRepo repo;
    @Autowired
    private TestDataHelper testDataHelper;

    @Given("Test data is prepared")
    public void testDataIsPrepared() {
        testDataHelper.clearTestEnvironment();
    }

    @When("I read message from ActiveMQ")
    public void readMessageFromActiveMQ() {
        jmsTemplate.convertAndSend(JmsConfig.WORKLOAD_QUEUE, scenarioContext.getExpectedMessage());
    }

    @Then("Entity is created in MongoDB")
    public void entityIsCreatedInMongoDB() {
        System.out.println("We're waiting 3 seconds!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        TrainerSummary expected = scenarioContext.getExpectedSummary();
        TrainerSummary actual = repo.findByUsername(scenarioContext.getExpectedMessage().getTrainerUsername())
                .orElseThrow(() -> new AssertionError("Trainer summary not found in MongoDB"));
        assertEquals(expected, actual, "Expected and actual trainer summaries do not match");
    }
}