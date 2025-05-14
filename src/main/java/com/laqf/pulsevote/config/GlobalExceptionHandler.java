package com.laqf.pulsevote.config;

import com.laqf.pulsevote.config.exception.OptionNotFoundException;
import com.laqf.pulsevote.config.exception.PollsNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<List<String>>> handleException(WebExchangeBindException e) {
        var errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return Mono.just(ResponseEntity.badRequest().body(errors));
    }



    @ExceptionHandler(PollsNotFoundException.class)
    public Mono<ResponseEntity<String>> handlePollsNotFoundException(PollsNotFoundException e) {
        return Mono.just(ResponseEntity
                .status(404) // o .notFound()
                .body(e.getMessage()));
    }

    @ExceptionHandler(OptionNotFoundException.class)
    public Mono<ResponseEntity<String>> handleOptionNotFoundException(OptionNotFoundException e) {
        return Mono.just(ResponseEntity
                .status(404) // o .notFound()
                .body(e.getMessage()));
    }
}
