package com.laqf.pulsevote.infrastructure.persistence.mapper;

import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.infrastructure.persistence.entity.PollEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPollsEntityMapper {

    Poll map(PollEntity pollEntity);

    PollEntity map(Poll poll);

}
