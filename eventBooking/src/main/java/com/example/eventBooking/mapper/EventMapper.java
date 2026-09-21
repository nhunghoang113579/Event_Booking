package com.example.eventBooking.mapper;

import com.example.eventBooking.dto.EventResponse;
import com.example.eventBooking.entity.Event;
import com.example.eventBooking.dto.EventRequest;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Component
public class EventMapper {
    public Event toEntity(EventRequest request) {
        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setDateTime(request.getDateTime());
        event.setLocation(request.getLocation());
        event.setLatitude(request.getLatitude());
        event.setLongitude(request.getLongitude());
        event.setPrice(request.getPrice());
        event.setDescription(request.getDescription());
        event.setImageUrl(request.getImageUrl());
        return event;
    }

    public EventResponse toResponse(Event event){
        EventResponse response = new EventResponse();
        response.setId(event.getId());
        response.setTitle(event.getTitle());
        response.setDateTime(event.getDateTime());
        response.setLocation(event.getLocation());
        response.setPrice(event.getPrice());
        response.setDescription(event.getDescription());
        response.setImageUrl(event.getImageUrl());
        return response;












    }


}
