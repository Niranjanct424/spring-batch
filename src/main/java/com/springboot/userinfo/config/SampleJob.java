package com.springboot.userinfo.config;

import com.springboot.userinfo.listener.FirstJobListener;
import com.springboot.userinfo.listener.FirstStepListener;
import com.springboot.userinfo.processor.FirstItemProcessor;
import com.springboot.userinfo.reader.FirstItemReader;
import com.springboot.userinfo.writer.FirstItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.userinfo.service.SecondTask;

@Configuration
public class SampleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SecondTask secondTask;

	@Autowired
	private FirstJobListener firstJobListener;

	@Autowired
	private FirstStepListener firstStepListener;

	@Autowired
	private FirstItemReader firstItemReader;

	@Autowired
	private FirstItemProcessor firstItemProcessor;

	@Autowired
	private FirstItemWriter firstItemWriter;

//	@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("First name")
				.incrementer(new RunIdIncrementer())
				.start(firstStep())
				.next(secondStep())
				.listener(firstJobListener)
				.build();
	}

	private Step firstStep() {
		return stepBuilderFactory.get("First Step").tasklet(firstTask())
				.listener(firstStepListener)
				.build();
	}
	
	private Step secondStep() {
		return stepBuilderFactory.get("Second Step").tasklet(secondTask).build();
	}

	private Tasklet firstTask() {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("This is First tasklet step ");
				System.out.println("SEC="+chunkContext.getStepContext());
				return RepeatStatus.FINISHED;
			}
		};

	}

	@Bean
	public Job secondJob(){
		return jobBuilderFactory.get("SecondJob")
				.incrementer(new RunIdIncrementer())
				.start(firstChunkStep())
				.next(secondStep())
				.build();
	}

	private Step firstChunkStep(){
		return stepBuilderFactory.get("First Chunk Step")
				.<Integer,Long>chunk(3)
				.reader(firstItemReader)
				.processor(firstItemProcessor)
				.writer(firstItemWriter)
				.build();
	}
	
	/*
	 * private Tasklet secondTask() { return new Tasklet() {
	 * 
	 * @Override public RepeatStatus execute(StepContribution contribution,
	 * ChunkContext chunkContext) throws Exception {
	 * System.out.println("This is second tasklet step "); return
	 * RepeatStatus.FINISHED; } };
	 * 
	 * }
	 */

}
