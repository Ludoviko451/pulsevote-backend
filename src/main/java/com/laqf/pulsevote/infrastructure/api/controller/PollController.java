package com.laqf.pulsevote.infrastructure.api.controller;

import com.laqf.pulsevote.aplication.usecase.CreatePollUseCase;
import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.infrastructure.api.dto.PollRequest;
import com.laqf.pulsevote.infrastructure.api.mappers.IPollRequestMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/polls")
public class PollController {

    private final CreatePollUseCase createPollUseCase;
    private final IPollRequestMapper pollRequestMapper;
    public PollController(CreatePollUseCase createPollUseCase, IPollRequestMapper pollRequestMapper) {
        this.createPollUseCase = createPollUseCase;
        this.pollRequestMapper = pollRequestMapper;
    }

    @PostMapping
    public Mono<Poll> createPoll(@RequestBody PollRequest pollRequest) {

        Poll poll = pollRequestMapper.map(pollRequest);

        return createPollUseCase.createPoll(poll);
    }
}
