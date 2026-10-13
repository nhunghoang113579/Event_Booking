package com.example.eventBooking.service.imply;

import com.example.eventBooking.dto.request.PaymentRequest;
import com.example.eventBooking.dto.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse pay(PaymentRequest request);
}
