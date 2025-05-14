package com.laqf.pulsevote.infrastructure.api.controller;

import com.laqf.pulsevote.aplication.usecase.CreatePollUseCase;
import com.laqf.pulsevote.aplication.usecase.GetAllPollsUseCase;
import com.laqf.pulsevote.aplication.usecase.VoteOptionUseCase;
import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.infrastructure.api.dto.PollRequest;
import com.laqf.pulsevote.infrastructure.api.dto.PollResponse;
import com.laqf.pulsevote.infrastructure.api.mappers.IPollRequestMapper;
import com.laqf.pulsevote.infrastructure.api.mappers.IPollResponseMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/polls")
public class PollController {

    private final CreatePollUseCase createPollUseCase;
    private final GetAllPollsUseCase getAllPollsUseCase;
    private final VoteOptionUseCase voteOptionUseCase;
    private final IPollRequestMapper pollRequestMapper;
    private final IPollResponseMapper pollResponseMapper;


    public PollController(CreatePollUseCase createPollUseCase, GetAllPollsUseCase getAllPollsUseCase, VoteOptionUseCase voteOptionUseCase, IPollRequestMapper pollRequestMapper, IPollResponseMapper pollResponseMapper) {
        this.createPollUseCase = createPollUseCase;
        this.getAllPollsUseCase = getAllPollsUseCase;
        this.voteOptionUseCase = voteOptionUseCase;
        this.pollRequestMapper = pollRequestMapper;
        this.pollResponseMapper = pollResponseMapper;
    }

    @PostMapping
    public Mono<ResponseEntity<Poll>> createPoll(@Valid @RequestBody PollRequest pollRequest) {

        return createPollUseCase.createPoll(pollRequestMapper.map(pollRequest))
                .map(poll -> ResponseEntity.status(HttpStatus.CREATED).body(poll));
    }

    @GetMapping
    public Mono<ResponseEntity<List<PollResponse>>> getAllPolls(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "true") boolean active
    ) {

        return getAllPollsUseCase.getAllPolls(page,size, active)
                .collectList()
                .map(polls -> ResponseEntity.ok(pollResponseMapper.map(polls)));
    }

    @PutMapping
    public Mono<ResponseEntity<Poll>> voteOption(@RequestParam String pollId, @RequestParam String optionId) {
        return voteOptionUseCase.voteOption(pollId, optionId)
                .map(ResponseEntity::ok);
    }

}
