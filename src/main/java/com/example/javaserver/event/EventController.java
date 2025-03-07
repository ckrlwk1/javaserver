package com.example.javaserver.event;

import com.example.javaserver.common.util.ApiResponseUtil;
import com.example.javaserver.event.dto.EventDto;
import com.example.javaserver.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:61941")
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;



    @RequestMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody EventDto eventDto) throws Exception {
        return ApiResponseUtil.getOkResponseEntity(eventService.createEvent(eventDto));
    }

    @RequestMapping("/list")
    public ResponseEntity<?> getAllEvent() throws Exception {
        return ApiResponseUtil.getOkResponseEntity(eventService.getAllEvent());
    }
}
