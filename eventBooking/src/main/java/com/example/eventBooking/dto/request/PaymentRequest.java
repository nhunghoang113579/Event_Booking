
package com.example.eventBooking.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PaymentRequest {

    @NotNull(message = "Booking is required")
    @Min(value = 1, message = "Booking ID must be greater than 0")
    private Long bookingId;

    @NotBlank(message = "Card number is required")
    @Pattern(
            regexp = "^(\\d{16}|\\d{4}( \\d{4}){3})$",
            message = "Invalid card number"
    )
    private String cardNumber;

    @NotBlank(message = "Expiry date is required")
    @Pattern(
            regexp = "^(0[1-9]|1[0-2])/\\d{2}$",
            message = "Invalid expiry format"
    )
    private String expiry;

    @NotBlank(message = "CVV is required")
    @Pattern(
            regexp = "^\\d{3,4}$",
            message = "Invalid CVV"
    )
    private String cvv;
}
