package com.laqf.pulsevote.infrastructure.persistence;

import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.domain.repository.PollRepository;
import com.laqf.pulsevote.infrastructure.persistence.mapper.IPollsEntityMapper;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PollRepositoryImpl implements PollRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final IPollsEntityMapper poolEntityMapper;

    public PollRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate, IPollsEntityMapper poolEntityMapper) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        this.poolEntityMapper = poolEntityMapper;
    }
    @Override
    public Mono<Poll> savePoll(Poll poll) {
        return Mono.just(poll)
                .map(poolEntityMapper::map)
                .flatMap(reactiveMongoTemplate::save)
                .map(poolEntityMapper::map);
    }

    @Override
    public Mono<Poll> getPollById(String id) {
        return null;
    }

    @Override
    public Flux<Poll> getAllPolls() {
        return null;
    }
}
