package com.example.javaserver.event.dto;

import lombok.Data;

@Data
public class EventDto {
    private String title;
    private String content;
    private String date;
    private String userId;

}
