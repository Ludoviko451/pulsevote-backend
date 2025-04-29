package com.laqf.pulsevote.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "polls")
public class PollEntity {

    @Id
    private String id;
    private String question;
    private List<OptionEntity> options;
    private LocalDate createdAt;
    private boolean active;
}
