package com.laqf.pulsevote.infrastructure.api.controller;

import com.laqf.pulsevote.aplication.usecase.CreatePollUseCase;
import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.infrastructure.api.dto.PollRequest;
import com.laqf.pulsevote.infrastructure.api.mappers.IPollRequestMapper;
import com.laqf.pulsevote.utils.Mocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
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
        PollRequest pollRequest = new PollRequest();
        pollRequest.setQuestion("test");
        pollRequest.setOptions(List.of());
        Poll poll = Mocks.mockPoll();

        when(createPollUseCase.createPoll(pollRequestMapper.map(pollRequest))).thenReturn(Mono.just(poll));

        webTestClient
                .post()
                .uri("/polls")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(poll), Poll.class)
                .exchange()
                .expectStatus().isCreated();
    }
}
