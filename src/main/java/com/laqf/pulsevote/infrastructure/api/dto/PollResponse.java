package com.laqf.pulsevote.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PollResponse {

    private String id;
    private String question;
    private List<OptionResponse> options;
    private LocalDate createdAt;
    private boolean active;
}
