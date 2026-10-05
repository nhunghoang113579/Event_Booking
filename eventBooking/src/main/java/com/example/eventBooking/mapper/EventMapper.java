package com.example.eventBooking.mapper;

import com.example.eventBooking.dto.response.EventResponse;
import com.example.eventBooking.entity.Event;
import com.example.eventBooking.dto.request.EventRequest;
import org.springframework.stereotype.Component;


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
