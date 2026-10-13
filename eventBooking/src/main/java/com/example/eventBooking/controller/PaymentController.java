package com.example.eventBooking.controller;

import com.example.eventBooking.dto.request.PaymentRequest;
import com.example.eventBooking.dto.response.ApiResponse;
import com.example.eventBooking.dto.response.PaymentResponse;
import com.example.eventBooking.service.imply.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentResponse>> payment(
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse response = paymentService.pay(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Payment successful",
                        response

                ));
    }

}
