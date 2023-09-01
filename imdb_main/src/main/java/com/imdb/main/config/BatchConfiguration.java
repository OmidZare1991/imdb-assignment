package com.imdb.main.config;

import com.imdb.main.domain.*;
import com.imdb.main.domain.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {
    private final ConfigProperties config;

    @Bean(name = "movie")
    protected Step stepMovie(
            JobRepository jobRepository
            , PlatformTransactionManager transactionManager
            , ItemReader<MovieDto> reader
            , ItemProcessor<MovieDto, Movie> processor
            , ItemWriter<Movie> writer
    ) {
        return new StepBuilder("movie", jobRepository).<MovieDto, Movie>chunk(config.getMovieStepBatchChunkSize(), transactionManager)
                .reader(reader)
                .processor(processor)
                .faultTolerant()
                .skip(Exception.class)
                .writer(writer)
                .build();
    }

    @Bean(name = "crew")
    protected Step stepCrew(
            JobRepository jobRepository
            , PlatformTransactionManager transactionManager, ItemReader<CrewDto> reader
            , ItemProcessor<CrewDto, Crew> processor
            , ItemWriter<Crew> writer
    ) {
        return new StepBuilder("crew", jobRepository).<CrewDto, Crew>chunk(config.getCrewStepBatchChunkSize(), transactionManager)
                .reader(reader)
                .processor(processor)
                .faultTolerant()
                .skip(Exception.class)
                .writer(writer)
                .build();
    }

    @Bean(name = "principals")
    protected Step stepPrincipals(
            JobRepository jobRepository
            , PlatformTransactionManager transactionManager
            , ItemReader<PrincipalDto> reader
            , ItemProcessor<PrincipalDto, Principal> processor
            , ItemWriter<Principal> writer
    ) {
        return new StepBuilder("principals", jobRepository).<PrincipalDto, Principal>chunk(config.getPrincipalsStepBatchChunkSize(), transactionManager).reader(reader).processor(processor).faultTolerant().skip(Exception.class).writer(writer).build();
    }

    @Bean(name = "rating")
    protected Step stepRating(
            JobRepository jobRepository
            , PlatformTransactionManager transactionManager
            , ItemReader<RatingDto> reader
            , ItemProcessor<RatingDto, Rating> processor
            , ItemWriter<Rating> writer
    ) {
        return new StepBuilder("rating", jobRepository).<RatingDto, Rating>chunk(config.getRatingStepBatchChunkSize(), transactionManager)
                .reader(reader)
                .processor(processor)
                .faultTolerant()
                .skip(Exception.class)
                .writer(writer)
                .build();
    }

    @Bean(name = "person")
    protected Step stepPerson(
            JobRepository jobRepository
            , PlatformTransactionManager transactionManager
            , ItemReader<PersonDto> reader
            , ItemProcessor<PersonDto, Person> processor
            , ItemWriter<Person> writer
    ) {
        return new StepBuilder("person", jobRepository).<PersonDto, Person>chunk(config.getPersonStepBatchChunkSize(), transactionManager)
                .reader(reader)
                .processor(processor)
                .faultTolerant()
                .skip(Exception.class)
                .writer(writer)
                .build();
    }

    @Bean
    @Autowired
    public Job imdbImportJob(
            JobRepository jobRepository
            , @Qualifier("movie") Step movieStep
            , @Qualifier("crew") Step crew
            , @Qualifier("principals") Step principal
            , @Qualifier("rating") Step rating
            , @Qualifier("person") Step person
    ) {
        return new JobBuilder("imdbImportJob", jobRepository)
                .start(movieStep)
                .next(crew)
                .next(person)
                .next(rating)
                .next(principal)
                .build();
    }
}
