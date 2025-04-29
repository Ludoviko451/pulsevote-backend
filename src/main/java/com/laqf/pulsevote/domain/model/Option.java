package com.laqf.pulsevote.domain.model;

import lombok.Getter;

@Getter

public class Option {
    private final int id;
    private final String text;
    private int votes;

    public Option(int id, String text) {
        this.id = id;
        this.text = text;
        this.votes = 0;
    }
}
