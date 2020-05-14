package com.madhu.spring.batch.configuration;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowConfiguration {

    private StepBuilderFactory stepBuilderFactory;
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
            System.out.println("Step 1 from inside flow foo");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2").tasklet((StepContribution contribution, ChunkContext ChunkContext) -> {
            System.out.println("Step 2 from inside flow foo");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Flow foo() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("foo");
        flowBuilder.start(step1()).on("COMPLETED").to(step2()).from(step2()).on("COMPLETED").end();
        return flowBuilder.build();
    }

    @Autowired
    public FlowConfiguration(StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobBuilderFactory = jobBuilderFactory;
    }
}