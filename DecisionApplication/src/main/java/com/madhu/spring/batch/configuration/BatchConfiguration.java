package com.madhu.spring.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.madhu.spring.batch.deciders.OddDecider;

/**
 * 
 * @author Madhu
 *
 */
@Configuration
public class BatchConfiguration {

	private StepBuilderFactory stepBuilderFactory;
	private JobBuilderFactory jobBuilderFactory;

	@Bean
	public Step startStep() {
		return stepBuilderFactory.get("startStep")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
					System.out.println("This is the start tasklet");
					return RepeatStatus.FINISHED;
				}).build();

	}

	@Bean
	public Step evenStep() {
		return stepBuilderFactory.get("evenStep").tasklet((StepContribution sc, ChunkContext cc) -> {
			System.out.println("This is the even tasklet");
			return RepeatStatus.FINISHED;
		}).build();
	}

	@Bean
	public Step oddStep() {
		return stepBuilderFactory.get("oddStep").tasklet((StepContribution sc, ChunkContext cc) -> {
			System.out.println("This is the odd tasklet");
			return RepeatStatus.FINISHED;
		}).build();
	}

	@Bean
	public JobExecutionDecider decider() {
		return new OddDecider();
	}

	@Bean
	public Job job() {
		return jobBuilderFactory.get("myJob2")
				.start(startStep())
				.next(decider())
				.from(decider()).on("ODD").to(oddStep())
				.from(decider()).on("EVEN").to(evenStep())
				.from(oddStep()).on("*").to(decider())
				.end().build();
	}

	@Autowired
	public BatchConfiguration(StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory) {
		super();
		this.stepBuilderFactory = stepBuilderFactory;
		this.jobBuilderFactory = jobBuilderFactory;
	}

}
