package com.madhu.spring.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madhu.spring.batch.sample.repository.SampleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringBatchDemoApplication implements CommandLineRunner{

	@Autowired
	SampleRepository repo;
	
	public static final Long longValue=1L; 
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBatchDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	
		ObjectMapper mapper = new ObjectMapper();
		repo.findAll().forEach(s->{
			try {
				log.info(mapper.writeValueAsString(s).toString());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

}
