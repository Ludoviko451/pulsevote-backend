package com.laqf.pulsevote;

import com.laqf.pulsevote.config.GlobalExceptionHandler;
import com.laqf.pulsevote.config.exception.PollsNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleWebExchangeBindException_ReturnsBadRequestWithErrorMessages() {

        WebExchangeBindException exception = mock(WebExchangeBindException.class);
        BindingResult bindingResult = mock(BindingResult.class);

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of(new ObjectError("error", "Error message 1"), new ObjectError("error", "Error message 2")));

        Mono<ResponseEntity<List<String>>> result = globalExceptionHandler.handleException(exception);

        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    assertEquals(400, response.getStatusCodeValue());
                    assertEquals(List.of("Error message 1", "Error message 2"), response.getBody());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void handlePollsNotFoundException_ReturnsNotFoundWithMessage() {

        String errorMessage = "No se encontraron encuestas";
        PollsNotFoundException exception = new PollsNotFoundException(errorMessage);


        Mono<ResponseEntity<String>> result = globalExceptionHandler.handlePollsNotFoundException(exception);

        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    assertEquals(404, response.getStatusCodeValue());
                    assertEquals(errorMessage, response.getBody());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void handlePollsNotFoundException_WithEmptyMessage_ReturnsNotFound() {

        PollsNotFoundException exception = new PollsNotFoundException("");


        Mono<ResponseEntity<String>> result = globalExceptionHandler.handlePollsNotFoundException(exception);

        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    assertEquals(404, response.getStatusCodeValue());
                    assertEquals("", response.getBody());
                    return true;
                })
                .verifyComplete();
    }
}