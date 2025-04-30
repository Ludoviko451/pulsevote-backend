package com.laqf.pulsevote.infrastructure.api.controller;

import com.laqf.pulsevote.aplication.usecase.CreatePollUseCase;
import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.infrastructure.api.dto.PollRequest;
import com.laqf.pulsevote.infrastructure.api.mappers.IPollRequestMapper;
import com.laqf.pulsevote.utils.Mocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PollControllerTest {


    private WebTestClient webTestClient;
    private CreatePollUseCase createPollUseCase;
    private IPollRequestMapper pollRequestMapper;

    @BeforeEach
    void setUp() {
        createPollUseCase = mock(CreatePollUseCase.class);
        pollRequestMapper = mock(IPollRequestMapper.class);
        webTestClient = WebTestClient.bindToController(
                new PollController(createPollUseCase, pollRequestMapper)
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
}
