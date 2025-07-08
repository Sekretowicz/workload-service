Feature: Reading messages from ActiveMQ

  Scenario: Read message from ActiveMQ and assert it's correct
    Given Test data is prepared
    When I read message from ActiveMQ
    Then Entity is created in MongoDB