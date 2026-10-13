package com.example.eventBooking.service.imply;

import com.example.eventBooking.dto.request.PaymentRequest;
import com.example.eventBooking.dto.response.PaymentResponse;
import com.example.eventBooking.entity.Booking;
import com.example.eventBooking.entity.BookingStatus;
import com.example.eventBooking.entity.Payment;
import com.example.eventBooking.entity.PaymentStatus;
import com.example.eventBooking.exception.PaymentBusinessException;
import com.example.eventBooking.exception.ResourceNotFoundException;
import com.example.eventBooking.repository.BookingRepository;
import com.example.eventBooking.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class PaymentServiceImply implements PaymentService{
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    @Override
    @Transactional
    public PaymentResponse pay(PaymentRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(()-> new ResourceNotFoundException("Booking not found"));

        if(booking.getStatus() == BookingStatus.PAID){
            throw new PaymentBusinessException(
                    "Booking is already paid",
                    HttpStatus.CONFLICT
            );
        }
        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new PaymentBusinessException(
                    "Booking cannot be paid",
                    HttpStatus.BAD_REQUEST
            );
        }

        Payment payment = Payment.builder()
                .booking(booking)
                .amount(booking.getTotalPrice())
                .status(PaymentStatus.PAID)
                .createdAt(LocalDateTime.now())
                .build();


        //5. Lưu payment
        Payment savedPayment = paymentRepository.save(payment);

        // 6. Cập nhật trạng thái booking
        booking.setStatus(BookingStatus.PAID);
        booking.setPayment(savedPayment);
        bookingRepository.save(booking);

        // 7. Trả response
        return PaymentResponse.builder()
                .paymentId(savedPayment.getId())
                .bookingId(booking.getId())
                .amount(savedPayment.getAmount())
                .status(savedPayment.getStatus())
                .build();
    }
}
