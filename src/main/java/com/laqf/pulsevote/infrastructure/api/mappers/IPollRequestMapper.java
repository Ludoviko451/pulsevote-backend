package com.laqf.pulsevote.infrastructure.api.mappers;

import com.laqf.pulsevote.domain.model.Option;
import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.infrastructure.api.dto.PollRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Mapper(componentModel = "spring")
public interface IPollRequestMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "active", expression = "java(true)")
    Poll map(PollRequest pollRequest);


    default List<Option> map(List<String> options){
        AtomicInteger atomicInteger = new AtomicInteger(1);
        return options.stream().map(option -> new Option(atomicInteger.getAndIncrement(), option)).toList();
    }
}
