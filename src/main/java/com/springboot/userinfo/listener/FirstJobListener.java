package com.springboot.userinfo.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Job name : "+jobExecution.getJobInstance().getJobName());
        System.out.println("params : "+jobExecution.getJobParameters());
        System.out.println("Job exe context : "+jobExecution.getExecutionContext());

        jobExecution.getExecutionContext().put("jec","jecValue");

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Job name : "+jobExecution.getJobInstance().getJobName());
        System.out.println("params : "+jobExecution.getJobParameters());
        System.out.println("Job exe context : "+jobExecution.getExecutionContext());
    }
}
