package com.laqf.pulsevote.infrastructure.api.mappers;

import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.infrastructure.api.dto.PollResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPollResponseMapper {

    PollResponse map(Poll poll);

    List<PollResponse> map(List<Poll> polls);
}
