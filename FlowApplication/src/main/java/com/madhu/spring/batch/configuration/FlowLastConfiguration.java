package com.madhu.spring.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class FlowLastConfiguration {
    
    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("myStep")
                .tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
                        System.out.println("myStep was executed");
                        return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job flowLastJob(Flow flow){
        return jobBuilderFactory.get("flowLastJob2").start(myStep()).on("COMPLETED").to(flow).end().build();
    }

    @Autowired
    public FlowLastConfiguration(final StepBuilderFactory stepBuilderFactory,
            final JobBuilderFactory jobBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobBuilderFactory = jobBuilderFactory;
    }
}