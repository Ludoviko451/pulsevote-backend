package com.laqf.pulsevote.aplication.usecase;


import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.domain.repository.PollRepository;
import com.laqf.pulsevote.utils.Mocks;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CreatePollUseCaseTest {

    @Test
    void createPoll() {
        Poll poll = Mocks.mockPoll();
        PollRepository pollRepository = Mockito.mock(PollRepository.class);
        when(pollRepository.savePoll(poll)).thenReturn(Mono.just(poll));
        CreatePollUseCase createPollUseCase = new CreatePollUseCase(pollRepository);
        Poll result = createPollUseCase.createPoll(poll).block();

        assert result != null;
        assertEquals(result.getId(), poll.getId());
        assertEquals(result.getQuestion(), poll.getQuestion());
    }
}
