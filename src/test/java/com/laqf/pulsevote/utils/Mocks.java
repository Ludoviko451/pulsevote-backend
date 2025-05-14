package com.laqf.pulsevote.utils;

import com.laqf.pulsevote.domain.model.Option;
import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.infrastructure.api.dto.OptionResponse;
import com.laqf.pulsevote.infrastructure.api.dto.PollRequest;
import com.laqf.pulsevote.infrastructure.api.dto.PollResponse;
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
        Option option = new Option(1, "option", 0);
        Option option2 = new Option(2, "option2", 0);
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

    public static PollRequest mockPollRequest() {
        return new PollRequest(DEFAULT_QUESTION, List.of("option", "option2"));
    }

    public static List<PollResponse> mockPollResponses() {
        OptionResponse option = new OptionResponse("1", "option", 0);
        OptionResponse option2 = new OptionResponse("2", "option2", 0);
        return List.of(new PollResponse(DEFAULT_ID, DEFAULT_QUESTION, List.of(option, option2), DEFAULT_DATE, DEFAULT_ACTIVE));
    }
}
