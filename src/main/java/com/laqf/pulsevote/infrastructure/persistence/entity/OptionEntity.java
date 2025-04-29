package com.laqf.pulsevote.infrastructure.persistence.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class OptionEntity {

    private String id;
    private String text;
    private int votes;
}
