package com.laqf.pulsevote.domain.model;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class Poll {

    private final String id;
    private final String question;
    private final List<Option> options;
    private final LocalDate createdAt;
    private final boolean active;

    public Poll(String id, String question, List<Option> options, LocalDate createdAt, boolean active) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.createdAt = createdAt;
        this.active = active;
    }
}
