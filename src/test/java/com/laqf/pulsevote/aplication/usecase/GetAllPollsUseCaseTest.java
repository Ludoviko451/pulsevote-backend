package com.laqf.pulsevote.aplication.usecase;


import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.domain.repository.PollRepository;
import com.laqf.pulsevote.utils.Mocks;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetAllPollsUseCaseTest {

    @Test
    void getAllPolls() {
        Flux<Poll> polls = Flux.just(Mocks.mockPoll());

        PollRepository pollRepository = Mockito.mock(PollRepository.class);
        when(pollRepository.getAllPolls(0, 10, true)).thenReturn(polls);
        GetAllPollsUseCase getAllPollsUseCase = new GetAllPollsUseCase(pollRepository);

        Flux<Poll> result = getAllPollsUseCase.getAllPolls(0, 10, true);

        assert result != null;
        assertEquals(result.blockFirst(), polls.blockFirst());
    }
}
