package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Duration;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	final static String TOPIC_NAME = "test";
	KafkaTemplate<String,String> template;

	public DemoApplication(KafkaTemplate<String, String> template) {
		this.template = template;
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			template.send(TOPIC_NAME,"스프링 카프카","테스트"+i);
		}
		System.exit(0);
	}
}
