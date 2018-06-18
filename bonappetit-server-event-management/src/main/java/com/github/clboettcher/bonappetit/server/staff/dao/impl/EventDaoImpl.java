package com.github.clboettcher.bonappetit.server.staff.dao.impl;

import com.github.clboettcher.bonappetit.server.staff.dao.EventDao;
import com.github.clboettcher.bonappetit.server.staff.entity.EventEntity;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class EventDaoImpl implements EventDao {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EventDaoImpl.class);


    @Autowired
    private EventRepository eventRepository;

    @Override
    public String getCurrentEvent() {
        List<EventEntity> eventEntities = Lists.newArrayList(eventRepository.findAll());
        if (eventEntities.size() > 1) {
            throw new IllegalStateException(String.format(
                    "Found more than one %s in the database.",
                    EventEntity.class.getSimpleName()
            ));
        } else if (CollectionUtils.isEmpty(eventEntities)) {
            return null;
        }

        return eventEntities.get(0).getCurrentEvent();
    }

    @Override
    public String setCurrentEvent(String event) {
        Preconditions.checkNotNull(event, "event");
        List<EventEntity> eventEntities = Lists.newArrayList(this.eventRepository.findAll());

        EventEntity eventEntity;

        if (CollectionUtils.isEmpty(eventEntities)) {
            LOGGER.info(String.format("Creating new event entity for current event %s", event));
            eventEntity = EventEntity.builder()
                    .currentEvent(event)
                    .build();
        } else {
            eventEntity = eventEntities.get(0);
            eventEntity.setCurrentEvent(event);
            LOGGER.info(String.format(
                    "Updating stored event with id %d to new event '%s'",
                    eventEntity.getId(),
                    event
            ));
        }

        EventEntity saved = this.eventRepository.save(eventEntity);
        return saved.getCurrentEvent();
    }
}
