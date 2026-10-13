package com.example.eventBooking.service.imply;

import com.example.eventBooking.dto.request.BookingRequest;
import com.example.eventBooking.dto.response.BookingDetailResponse;
import com.example.eventBooking.dto.response.BookingResponse;
import com.example.eventBooking.dto.response.FieldError;
import com.example.eventBooking.entity.Booking;
import com.example.eventBooking.entity.BookingStatus;
import com.example.eventBooking.entity.Event;
import com.example.eventBooking.entity.User;
import com.example.eventBooking.exception.ResourceNotFoundException;
import com.example.eventBooking.exception.ValidationException;
import com.example.eventBooking.mapper.BookingMapper;
import com.example.eventBooking.repository.BookingRepository;
import com.example.eventBooking.repository.EventRepository;
import com.example.eventBooking.repository.UserRepository;
import com.example.eventBooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImply implements BookingService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    @Override
    public BookingResponse create(BookingRequest request) {
        List<FieldError> errors = new ArrayList<>();


        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        // 1. Tìm Event
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found")
                );

        // 2. Lấy user đang đăng nhập từ JWT
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        // 3. Request -> Booking
        Booking booking = bookingMapper.bookingToEntity(request);


        // 4. Gán User
        booking.setUser(user);

        // 5. Gán Event
        booking.setEvent(event);

        // 6. Tính tổng tiền
        BigDecimal totalPrice =
                event.getPrice()
                        .multiply(BigDecimal.valueOf(request.getQuantity()));

        booking.setTotalPrice(totalPrice);

        // 7. Thời gian tạo
        booking.setCreatedAt(LocalDateTime.now());
        booking.setStatus(BookingStatus.PENDING);

        // 8. Lưu Database
        Booking saveBooking = bookingRepository.save(booking);

        // 9. Booking -> Response
        return bookingMapper.toResponse(saveBooking);
    }

    @Override
    public List<BookingResponse> getAll() {

        List<Booking> bookings = bookingRepository.findAll();

        List<BookingResponse> responses = new ArrayList<>();

        for (Booking booking : bookings) {
            responses.add(bookingMapper.toResponse(booking));
        }

        return responses;
    }



    @Override
    public BookingDetailResponse getById(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found with id: " + id
                        )
                );

        return bookingMapper.toDetailResponse(booking);
    }





}