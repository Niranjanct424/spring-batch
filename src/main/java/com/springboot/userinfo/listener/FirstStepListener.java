package com.springboot.userinfo.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements org.springframework.batch.core.StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("JEC "+stepExecution.getJobExecution().getExecutionContext());
        System.out.println("SEC "+stepExecution.getExecutionContext());
        System.out.println("params : "+stepExecution.getJobParameters());
        stepExecution.getExecutionContext().put("SEC","sec Value");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("JEC "+stepExecution.getJobExecution().getExecutionContext());
        System.out.println("SEC "+stepExecution.getExecutionContext());
        System.out.println("params : "+stepExecution.getJobParameters());
        return ExitStatus.COMPLETED;
    }
}
