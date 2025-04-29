package com.laqf.pulsevote.infrastructure.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PollRequest {

    private String  question;
    private List<String> options;

}
