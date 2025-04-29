package com.laqf.pulsevote.infrastructure.persistence;

import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.infrastructure.persistence.entity.PollEntity;
import com.laqf.pulsevote.infrastructure.persistence.mapper.IPollsEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.*;

public class PollRepositoryImplTest {

    private ReactiveMongoTemplate reactiveMongoTemplate;
    private  IPollsEntityMapper pollEntityMapper;
    private PollRepositoryImpl pollRepository;

    @BeforeEach
    void setUp() {
        reactiveMongoTemplate = mock(ReactiveMongoTemplate.class);
        pollEntityMapper = mock(IPollsEntityMapper.class);

        pollRepository = new PollRepositoryImpl(reactiveMongoTemplate, pollEntityMapper);
    }

    @Test
    void savePollTest() {
        Poll poll = new Poll("1", "test", List.of(), LocalDate.now(), true);
        PollEntity pollEntity = new PollEntity("1", "test", List.of(), LocalDate.now(), true);

        // Stubbing del mapper y template
        when(pollEntityMapper.map(poll)).thenReturn(pollEntity);
        when(reactiveMongoTemplate.save(pollEntity)).thenReturn(Mono.just(pollEntity));
        when(pollEntityMapper.map(pollEntity)).thenReturn(poll);

        // Ejecutar el método
        Mono<Poll> result = pollRepository.savePoll(poll);

        // Verificación con StepVerifier
        StepVerifier.create(result)
                .expectNext(poll)
                .verifyComplete();

        // Verifica que los mocks fueron llamados
        verify(pollEntityMapper).map(poll);
        verify(reactiveMongoTemplate).save(pollEntity);
        verify(pollEntityMapper).map(pollEntity);
    }
}
