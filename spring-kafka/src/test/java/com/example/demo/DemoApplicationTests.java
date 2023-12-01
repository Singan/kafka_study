package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
class DemoApplicationTests {
    KafkaTemplate<String,String> kafkaTemplate;

    final String TOPIC_NAME = "test";
    @Test
    void contextLoads() {
    }

    @Test
    void customKafkaTemplateTest() {
        kafkaTemplate.send(TOPIC_NAME,"테스트1입니다");
    }
}
