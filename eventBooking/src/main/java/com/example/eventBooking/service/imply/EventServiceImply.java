package com.example.eventBooking.service.imply;

import com.example.eventBooking.dto.EventRequest;
import com.example.eventBooking.dto.EventResponse;
import com.example.eventBooking.entity.Event;
import com.example.eventBooking.exception.FieldErrorResponse;
import com.example.eventBooking.exception.ResourceNotFoundException;
import com.example.eventBooking.mapper.EventMapper;
import com.example.eventBooking.repository.EventRepository;
import com.example.eventBooking.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.eventBooking.exception.FieldErrorResponse;
import com.example.eventBooking.exception.ValidationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImply implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;


    // =========================
    // GET ALL
    // =========================

    @Override
    public List<EventResponse> getAllEvent() {

        // Lấy tất cả Event
        List<Event> events = eventRepository.findAll();

        // Tạo list kết quả
        List<EventResponse> responses = new ArrayList<>();

        // Event → EventResponse
        for (Event event : events) {
            responses.add(eventMapper.toResponse(event));
        }

        return responses;
    }


    // =========================
    // GET BY ID
    // =========================

    @Override
    public EventResponse getEventById(Long id) {

        // Tìm Event
        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found")
                );

        // Event → Response
        return eventMapper.toResponse(event);
    }


    // =========================
    // CREATE
    // =========================

    @Override
    public EventResponse createEvent(EventRequest request) {

        // Request → Event
        Event event = eventMapper.toEntity(request);

        // Lưu Database
        Event savedEvent = eventRepository.save(event);

        // Event → Response
        return eventMapper.toResponse(savedEvent);
    }


    // =========================
    // UPDATE
    // =========================

    @Override
    public EventResponse updateEvent(
            Long id,
            EventRequest request
    ) {

        // Tìm Event
        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found")
                );

        // Cập nhật dữ liệu
        event.setTitle(request.getTitle());
        event.setDateTime(request.getDateTime());
        event.setLocation(request.getLocation());
        event.setLatitude(request.getLatitude());
        event.setLongitude(request.getLongitude());
        event.setPrice(request.getPrice());
        event.setDescription(request.getDescription());
        event.setImageUrl(request.getImageUrl());

        // Lưu Database
        Event updatedEvent = eventRepository.save(event);

        // Event → Response
        return eventMapper.toResponse(updatedEvent);
    }


    // =========================
    // DELETE
    // =========================

    @Override
    public void deleteEvent(Long id) {

        // Tìm Event
        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found")
                );

        // Xóa Event
        eventRepository.delete(event);
    }


    // =========================
    // GET EVENT LIST
    // =========================

    @Override
    public List<EventResponse> getEvents(
            String type,
            String search,
            Double userLat,
            Double userLng,
            int page,
            int size
    ) {

        // 1. Tạo danh sách chứa lỗi
        List<FieldErrorResponse> errors = new ArrayList<>();

        // 2. Kiểm tra type
        if (type != null &&
                !type.equals("popular") &&
                !type.equals("upcoming") &&
                !type.equals("nearby")) {

            errors.add(
                    new FieldErrorResponse(
                            "type",
                            "Invalid type value"
                    )
            );
        }

        // 3. Kiểm tra page
        if (page < 1) {
            errors.add(
                    new FieldErrorResponse(
                            "page",
                            "Page must be a positive integer"
                    )
            );
        }

        // 4. Kiểm tra size
        if (size < 1) {
            errors.add(
                    new FieldErrorResponse(
                            "size",
                            "Size must be a positive integer"
                    )
            );
        }

        // 5. Nếu có lỗi thì throw
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        // 6. Tạo Pageable
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Event> events;

        // 7. Search
        if (search != null && !search.trim().isEmpty()) {

            events = eventRepository.searchEvents(
                    search.trim(),
                    pageable
            );

            // 8. Popular
        } else if ("popular".equals(type)) {

            events = eventRepository.findPopular(pageable);

            // 9. Upcoming
        } else if ("upcoming".equals(type)) {

            events = eventRepository.findUpcoming(
                    LocalDateTime.now(),
                    pageable
            );

            // 10. Nearby
        } else if ("nearby".equals(type)) {

            if (userLat == null || userLng == null) {

                List<FieldErrorResponse> locationErrors =
                        new ArrayList<>();

                if (userLat == null) {
                    locationErrors.add(
                            new FieldErrorResponse(
                                    "userLat",
                                    "User latitude is required"
                            )
                    );
                }

                if (userLng == null) {
                    locationErrors.add(
                            new FieldErrorResponse(
                                    "userLng",
                                    "User longitude is required"
                            )
                    );
                }

                throw new ValidationException(locationErrors);
            }

            events = eventRepository.findNearby(
                    userLat,
                    userLng,
                    pageable
            );

            // 11. Không có type
        } else {

            events = eventRepository.findAll(pageable);
        }

        // 12. Entity → Response
        return events.getContent()
                .stream()
                .map(eventMapper::toResponse)
                .toList();
    }
}