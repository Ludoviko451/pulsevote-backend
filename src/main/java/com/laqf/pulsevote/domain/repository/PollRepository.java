package com.laqf.pulsevote.domain.repository;

import com.laqf.pulsevote.domain.model.Poll;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PollRepository {

    Mono<Poll> savePoll(Poll poll);
    Mono<Poll> getPollById(String id);
    Flux<Poll> getAllPolls();
}
