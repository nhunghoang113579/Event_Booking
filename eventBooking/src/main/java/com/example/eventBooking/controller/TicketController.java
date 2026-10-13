
package com.example.eventBooking.controller;

import com.example.eventBooking.dto.response.ApiResponse;
import com.example.eventBooking.dto.response.TicketResponse;
import com.example.eventBooking.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TicketResponse>>> getTicket() {

        List<TicketResponse> response = ticketService.getTickets();

        ApiResponse<List<TicketResponse>> apiResponse =
                ApiResponse.<List<TicketResponse>>builder()
                        .success(true)
                        .message("Tickets fetched successfully")
                        .data(response)
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
}
