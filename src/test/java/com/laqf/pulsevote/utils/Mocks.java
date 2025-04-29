package com.laqf.pulsevote.utils;

import com.laqf.pulsevote.domain.model.Option;
import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.infrastructure.persistence.entity.OptionEntity;
import com.laqf.pulsevote.infrastructure.persistence.entity.PollEntity;

import java.time.LocalDate;
import java.util.List;

public class Mocks {

    private static final String DEFAULT_ID = "1";
    private static final String DEFAULT_QUESTION = "question";
    private static final LocalDate DEFAULT_DATE = LocalDate.now();
    private static final boolean DEFAULT_ACTIVE = true;

    public static Poll mockPoll() {
        return new Poll(DEFAULT_ID, DEFAULT_QUESTION, List.of(), DEFAULT_DATE, DEFAULT_ACTIVE);
    }

    public static Poll mockPollWithOptions() {
        Option option = new Option(1, "option");
        Option option2 = new Option(2, "option2");
        return new Poll(DEFAULT_ID, DEFAULT_QUESTION, List.of(option, option2), DEFAULT_DATE, DEFAULT_ACTIVE);
    }

    public static PollEntity mockPollEntity() {
        return new PollEntity(DEFAULT_ID, DEFAULT_QUESTION, List.of(), DEFAULT_DATE, DEFAULT_ACTIVE);
    }

    public static PollEntity mockPollEntityWithOptions() {
        OptionEntity option = new OptionEntity("1", "option", 0);
        OptionEntity option2 = new OptionEntity("2", "option2", 0);
        return new PollEntity(DEFAULT_ID, DEFAULT_QUESTION, List.of(option, option2), DEFAULT_DATE, DEFAULT_ACTIVE);
    }
}
