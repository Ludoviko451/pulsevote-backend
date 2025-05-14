package com.laqf.pulsevote.infrastructure.api.controller;

import com.laqf.pulsevote.aplication.usecase.CreatePollUseCase;
import com.laqf.pulsevote.aplication.usecase.GetAllPollsUseCase;
import com.laqf.pulsevote.aplication.usecase.VoteOptionUseCase;
import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.infrastructure.api.dto.PollRequest;
import com.laqf.pulsevote.infrastructure.api.dto.PollResponse;
import com.laqf.pulsevote.infrastructure.api.mappers.IPollRequestMapper;
import com.laqf.pulsevote.infrastructure.api.mappers.IPollResponseMapper;
import com.laqf.pulsevote.utils.Mocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PollControllerTest {


    private WebTestClient webTestClient;
    private CreatePollUseCase createPollUseCase;
    private GetAllPollsUseCase getAllPollsUseCase;
    private VoteOptionUseCase voteOptionUseCase;
    private IPollRequestMapper pollRequestMapper;
    private IPollResponseMapper pollResponseMapper;

    @BeforeEach
    void setUp() {
        createPollUseCase = mock(CreatePollUseCase.class);
        getAllPollsUseCase = mock(GetAllPollsUseCase.class);
        pollRequestMapper = mock(IPollRequestMapper.class);
        pollResponseMapper = mock(IPollResponseMapper.class);
        webTestClient = WebTestClient.bindToController(
                new PollController(createPollUseCase, getAllPollsUseCase, voteOptionUseCase, pollRequestMapper, pollResponseMapper)
        ).build();
    }


    @Test
    void createPollTest() {
        PollRequest pollRequest = Mocks.mockPollRequest();
        Poll poll = Mocks.mockPollWithOptions();

        when(createPollUseCase.createPoll(pollRequestMapper.map(pollRequest))).thenReturn(Mono.just(poll));

        webTestClient
                .post()
                .uri("/polls")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(pollRequest), PollRequest.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void getAllPollsTest() {

        Poll poll = Mocks.mockPollWithOptions();
        List<PollResponse> pollResponses = Mocks.mockPollResponses();
        List<Poll> polls = List.of(poll);

        when(getAllPollsUseCase.getAllPolls(0, 10, true)).thenReturn(Flux.fromIterable(polls));

        when(pollResponseMapper.map(polls)).thenReturn(pollResponses);

        webTestClient
                .get()
                .uri("/polls")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PollResponse.class);
    }

}
