package com.madhu.spring.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class FlowFirstConfiguration {

    private StepBuilderFactory stepBuilderFactory;
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("myStep").tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
            System.out.println("myStep was executed");
            return null;
        }).build();
    }

    @Bean
    public Job flowFirstJob(Flow flow) {
        return jobBuilderFactory.get("flowFirstJob2").start(flow).next(myStep()).end().build();
    }

    @Autowired
    public FlowFirstConfiguration(StepBuilderFactory sbf, JobBuilderFactory jbf) {
        this.stepBuilderFactory = sbf;
        this.jobBuilderFactory = jbf;
    }

}