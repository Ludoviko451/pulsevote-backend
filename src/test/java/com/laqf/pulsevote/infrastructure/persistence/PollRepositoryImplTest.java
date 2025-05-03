package com.laqf.pulsevote.infrastructure.persistence;

import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.infrastructure.persistence.entity.PollEntity;
import com.laqf.pulsevote.infrastructure.persistence.mapper.IPollsEntityMapper;
import com.laqf.pulsevote.utils.Mocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;
import static org.mockito.Mockito.*;

class PollRepositoryImplTest {

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
        Poll poll = Mocks.mockPoll();
        PollEntity pollEntity = Mocks.mockPollEntity();

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

    @Test
    void getAllPollsTest() {
        PollEntity entity = Mocks.mockPollEntity();
        Poll domainPoll = Mocks.mockPoll();

        when(reactiveMongoTemplate.find(any(Query.class), eq(PollEntity.class)))
                .thenReturn(Flux.just(entity));

        when(pollEntityMapper.map(entity)).thenReturn(domainPoll);

        StepVerifier.create(pollRepository.getAllPolls(0, 10, true))
                .expectNext(domainPoll)
                .verifyComplete();
    }
}
