package com.example.javaserver.event.service;

import com.example.javaserver.common.code.CodeName;
import com.example.javaserver.common.code.ResponseCode;
import com.example.javaserver.common.code.ServiceType;
import com.example.javaserver.common.jpa.entity.EventEntity;
import com.example.javaserver.common.jpa.repository.EventRepository;
import com.example.javaserver.event.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public ResponseCode createEvent(EventDto eventDto) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setTitle(eventDto.getTitle());
        eventEntity.setContent(eventDto.getContent());
        eventEntity.setUserId("han");
        eventEntity.setDate(eventDto.getDate());
        eventRepository.save(eventEntity);

        System.out.println("eventDto : " + eventDto);
        System.out.println("eventEntity : " + eventEntity);

        return new ResponseCode(ServiceType.API, CodeName.SUCCESS);
    }

    public ResponseCode getAllEvent() {

        List<EventEntity> list  = eventRepository.findByUserId("han");

        return new ResponseCode(ServiceType.API, CodeName.SUCCESS, list);
    }
}
