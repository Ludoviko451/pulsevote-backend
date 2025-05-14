package com.laqf.pulsevote.infrastructure.persistence.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OptionEntity {

    private String id;
    private String text;
    private int votes;
}
