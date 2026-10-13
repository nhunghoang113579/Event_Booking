
package com.example.eventBooking.service.imply;

import com.example.eventBooking.dto.response.BookingEventResponse;
import com.example.eventBooking.dto.response.TicketResponse;
import com.example.eventBooking.entity.Booking;
import com.example.eventBooking.entity.User;
import com.example.eventBooking.exception.ResourceNotFoundException;
import com.example.eventBooking.repository.BookingRepository;
import com.example.eventBooking.repository.UserRepository;
import com.example.eventBooking.service.TicketService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

        @Service
        @RequiredArgsConstructor
        public class TicketServiceImply implements TicketService {

            private final UserRepository userRepository;
            private final BookingRepository bookingRepository;

            @Override
            public List<TicketResponse> getTickets() {

                // 1. Lấy user đang đăng nhập từ SecurityContext
                Authentication authentication =
                        SecurityContextHolder.getContext().getAuthentication();

                String email = authentication.getName();

                // 2. Tìm user trong database
                User user = userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("User not found")
                        );

                // 3. Lấy danh sách booking của user
                List<Booking> bookings =
                        bookingRepository.findByUserId(user.getId());

                // 4. Chuyển Booking thành TicketResponse
                return bookings.stream()
                        .map(booking -> TicketResponse.builder()
                                .ticketId(booking.getId())
                                .event(BookingEventResponse.builder()
                                        .id(booking.getEvent().getId())
                                        .title(booking.getEvent().getTitle())
                                        .dateTime(booking.getEvent().getDateTime())
                                        .location(booking.getEvent().getLocation())
                                        .build())
                                .quantity(booking.getQuantity())
                                .status(booking.getStatus())
                                .build())
                        .toList();
            }
        }



