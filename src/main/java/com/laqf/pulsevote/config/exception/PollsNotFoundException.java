package com.laqf.pulsevote.config.exception;

public class PollsNotFoundException extends RuntimeException {
    public PollsNotFoundException(String pollsNotFound) {
        super(pollsNotFound);
    }
}
