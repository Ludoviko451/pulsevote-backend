package com.laqf.pulsevote.aplication.usecase;

import com.laqf.pulsevote.config.exception.PollsNotFoundException;
import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.domain.repository.PollRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllPollsUseCase {

    private final PollRepository pollRepository;

    public GetAllPollsUseCase(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Flux<Poll> getAllPolls(int page, int size, boolean active) {
        return pollRepository.getAllPolls(page,size, active)
                .switchIfEmpty(Flux.error(new PollsNotFoundException("Polls not found")));
    }
}
