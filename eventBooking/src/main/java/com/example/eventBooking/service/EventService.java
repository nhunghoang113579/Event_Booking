package com.example.eventBooking.service;

import com.example.eventBooking.dto.EventRequest;
import com.example.eventBooking.dto.EventResponse;
import com.example.eventBooking.entity.Event;

import java.util.List;


public interface EventService {
//   List<EventResponse> getAllEvent();
   EventResponse getEventById(Long id);
   EventResponse createEvent(EventRequest request);
   EventResponse updateEvent(Long id, EventRequest request);
   void deleteEvent(Long id);

    // Lấy danh sách Event
    // Get event list
    List<EventResponse> getEvents(
            String type,
            String search,
            Double userLat,
            Double userLng,
            int page,
            int size
    );

}
