package com.laqf.pulsevote.infrastructure.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PollRequest {

    @NotBlank(message = "Question cannot be blank")
    private String  question;
    @NotEmpty(message = "Options cannot be empty")
    private List<String> options;

}
