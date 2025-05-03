package com.laqf.pulsevote.infrastructure.persistence.mapper;

import com.laqf.pulsevote.domain.model.Option;
import com.laqf.pulsevote.domain.model.Poll;
import com.laqf.pulsevote.infrastructure.persistence.entity.OptionEntity;
import com.laqf.pulsevote.infrastructure.persistence.entity.PollEntity;
import com.laqf.pulsevote.utils.Mocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PollsEntityMapperTest {
    private IPollsEntityMapper mapper;
    @BeforeEach
    void setUp() {
        mapper = new IPollsEntityMapperImpl();
    }

    @Test
    void testMapEntity() {
        Poll poll = Mocks.mockPollWithOptions();


        PollEntity pollEntity = mapper.map(poll);

        assertNotNull(pollEntity);
        assertEquals("1", pollEntity.getId());
        assertEquals("question", pollEntity.getQuestion());
        assertEquals("option", pollEntity.getOptions().get(0).getText());
        assertEquals("option2", pollEntity.getOptions().get(1).getText());
        assertEquals(0, pollEntity.getOptions().get(0).getVotes());

    }

    @Test
    void testMapDomain() {
        PollEntity pollEntity = Mocks.mockPollEntityWithOptions();
        Poll poll = mapper.map(pollEntity);

        assertNotNull(poll);
        assertEquals("1", poll.getId());
        assertEquals("question", poll.getQuestion());
        assertEquals("option", poll.getOptions().get(0).getText());
        assertEquals("option2", poll.getOptions().get(1).getText());
        assertEquals(0, poll.getOptions().get(0).getVotes());
}

}
