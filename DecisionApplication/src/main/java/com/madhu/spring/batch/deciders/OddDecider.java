package com.madhu.spring.batch.deciders;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import lombok.Getter;

/**
 * 
 * @author Madhu
 *
 */
@Getter
public class OddDecider implements JobExecutionDecider{

	private int count = 0;
	private static final String EVEN = "EVEN";
	private static final String ODD = "ODD";
	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
		count++;
		System.out.println(count);
		if(count % 2 == 0)
			return new FlowExecutionStatus(EVEN);
		else
			return new FlowExecutionStatus(ODD);
	}

}
