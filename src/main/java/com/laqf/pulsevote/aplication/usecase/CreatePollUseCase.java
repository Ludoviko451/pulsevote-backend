package com.laqf.pulsevote.aplication.usecase;

import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.domain.repository.PollRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreatePollUseCase {
    private  final PollRepository pollRepository;

    public CreatePollUseCase(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Mono<Poll> createPoll(Poll poll) {
        return pollRepository.savePoll(poll);
    }
}
