package com.sekretowicz.workload_service.messaging.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sekretowicz.workload_service.messaging.dto.WorkloadDto;
import jakarta.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import java.util.Map;

@Configuration
@EnableJms
public class JmsConfig {
    public static final String WORKLOAD_QUEUE = "workload.queue";

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

        converter.setTargetType(MessageType.TEXT);
        converter.setObjectMapper(objectMapper);

        converter.setTypeIdPropertyName("_type");

        converter.setTypeIdMappings(Map.of(
                "Workload", WorkloadDto.class
        ));

        return converter;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, MessageConverter converter) {
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
}