package com.imdb.main;

import com.imdb.main.config.ConfigProperties;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class ImdbApplication {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;
    @Autowired
    private ConfigProperties configProperties;

    public static void main(String[] args) {
        SpringApplication.run(ImdbApplication.class, args);
    }

    @Scheduled(cron = "${spring.batch.job.cron}")
    public void perform() throws Exception {

        JobParameters params = new JobParametersBuilder().addString("JobID", UUID.randomUUID().toString()).toJobParameters();

        jobLauncher.run(job, params);
    }
}
