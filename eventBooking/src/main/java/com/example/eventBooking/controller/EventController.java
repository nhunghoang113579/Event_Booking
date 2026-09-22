package com.example.eventBooking.controller;

import com.example.eventBooking.dto.ApiResponse;
import com.example.eventBooking.dto.EventRequest;
import com.example.eventBooking.dto.EventResponse;
import com.example.eventBooking.entity.Event;
import com.example.eventBooking.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private  final EventService eventService;

    //Create
    @PostMapping
    public ResponseEntity<ApiResponse<EventResponse>>createEvent(@Valid @RequestBody EventRequest request){
        EventResponse event = eventService.createEvent(request);

        ApiResponse<EventResponse> response = new ApiResponse<>(
                true,
                "Event created successfully",
                event
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);


    }


//Update
    @PutMapping("/{id}")
    public  ResponseEntity<EventResponse>updateEvent( @PathVariable Long id,@Valid @RequestBody EventRequest request){
        EventResponse eventUpdate = eventService.updateEvent(id,request);

        return  ResponseEntity.status(HttpStatus.OK).body(eventUpdate);
    }

//Delete

@DeleteMapping("/{id}")

public ResponseEntity<Void>deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
}

//GetAll

//    @GetMapping("/all")
//
//    public  ResponseEntity<List<EventResponse>>getAllEvent(
//            @RequestParam(required = false)String type,
//            @RequestParam(required = false)String search,
//            @RequestParam(defaultValue = "1") Integer page,
//            @RequestParam(defaultValue = "10") Integer size
//    ){
//        List<EventResponse> allEvent = eventService.getAllEvent();
//        return ResponseEntity.status(HttpStatus.OK).body(allEvent);
//
//    }

//e. Chi tiết sự kiện getById
    @GetMapping("/{id}")

    public ResponseEntity<ApiResponse<EventResponse>>getByIdEvent(@PathVariable Long id){
        EventResponse event = eventService.getEventById(id);

        ApiResponse<EventResponse> response = new ApiResponse<>(
                true,
                "Event detail fetched successfully",
                event
        );


        return ResponseEntity.status(HttpStatus.OK).body(response);
    }




    @GetMapping
    public ResponseEntity<ApiResponse<List<EventResponse>>> getEvents(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Double userLat,
            @RequestParam(required = false) Double userLng,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        List<EventResponse> events =
                eventService.getEvents(
                        type,
                        search,
                        userLat,
                        userLng,
                        page,
                        size
                );

        ApiResponse<List<EventResponse>> response =
                new ApiResponse<>(
                        true,
                        "Events fetched successfully",
                        events
                );


        return ResponseEntity.ok(response);
    }





}
