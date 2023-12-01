package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class DemoApplication  implements ApplicationRunner {
	final static String TOPIC_NAME = "test";

	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;


	public static void main(String[] args) {
		System.out.println("시작");
		SpringApplication.run(DemoApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		kafkaTemplate.send(TOPIC_NAME,"스프링 카프카","커스텀 테스트6");

		System.out.println("끝");
	}

//	@Override
//	public void run(String... args) throws Exception {
//		for (int i = 0; i < 10; i++) {
//			template.send(TOPIC_NAME,"스프링 카프카","테스트"+i);
//		}
//		System.exit(0);
//	}
}
