package com.laqf.pulsevote.aplication.usecase;

import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.domain.repository.PollRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VoteOptionUseCase {

    private final PollRepository pollRepository;

    public VoteOptionUseCase(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Mono<Poll> voteOption(String pollId, String optionId) {
        return pollRepository.voteOption(pollId, optionId);
    }
}
