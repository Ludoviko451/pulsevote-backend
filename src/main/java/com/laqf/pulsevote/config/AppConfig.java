package com.laqf.pulsevote.config;

import com.laqf.pulsevote.aplication.usecase.CreatePollUseCase;
import com.laqf.pulsevote.domain.repository.PollRepository;
import com.laqf.pulsevote.infrastructure.persistence.PollRepositoryImpl;
import com.laqf.pulsevote.infrastructure.persistence.mapper.IPollsEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final IPollsEntityMapper poolEntityMapper;
    @Bean
    public CreatePollUseCase createPollUseCase(PollRepository pollRepository) {
        return new CreatePollUseCase(pollRepository);
    }

    @Bean
    public PollRepository pollRepository(ReactiveMongoTemplate mongoTemplate) {
        return new PollRepositoryImpl(mongoTemplate, poolEntityMapper);
    }
}
