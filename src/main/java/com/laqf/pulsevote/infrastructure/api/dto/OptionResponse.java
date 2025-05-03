package com.laqf.pulsevote.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class OptionResponse {

    private String id;
    private String text;
    private int votes;

}
