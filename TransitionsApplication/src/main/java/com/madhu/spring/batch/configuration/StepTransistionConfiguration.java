/**
 * 
 */
package com.madhu.spring.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Madhu
 *
 */
@Configuration
public class StepTransistionConfiguration {

	public JobBuilderFactory jobBuilderFactory;

	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				// TODO Auto-generated method stub
				System.out.println(">> This is Step 1");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").tasklet((contribution, chunkContext) -> {
			System.out.println(">> This is Step2");
			return RepeatStatus.FINISHED;
		}).build();
	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				// TODO Auto-generated method stub
				System.out.println(">> This is Step 3");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	
	@Bean
	public Job transistionJobSimple() {
//		return jobBuilderFactory.get("transistionJobNextRun2").start(step1()).next(step2()).next(step2()).next(step3()).build();

		// 
		//		return jobBuilderFactory.get("transistionJobNextRun3").start(step1()).on("COMPLETED").to(step2()).from(step2())
//				.on("COMPLETED").to(step3()).from(step3()).end().build();

		//Fail a step		
//		return jobBuilderFactory.get("transistionJobNextRun4").start(step1()).on("COMPLETED").to(step2()).from(step2())
//				.on("COMPLETED").fail().from(step3()).end().build();
		
		//stop and restart step
		return jobBuilderFactory.get("transistionJobNextRun5").start(step1()).on("COMPLETED").to(step2()).from(step2())
				.on("COMPLETED").stopAndRestart(step3()).from(step3()).end().build();
	}

	public StepTransistionConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		super();
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

}
