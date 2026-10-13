
package com.example.eventBooking.exception;

import org.springframework.http.HttpStatus;

public class PaymentBusinessException extends BusinessException {

    private final HttpStatus status;

    public PaymentBusinessException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
