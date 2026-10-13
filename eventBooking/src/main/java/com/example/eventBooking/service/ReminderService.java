package com.example.eventBooking.service;

import com.example.eventBooking.dto.request.ReminderRequest;
import com.example.eventBooking.dto.response.ReminderResponse;

public interface ReminderService {
    ReminderResponse updateReminder(
            String email,
            ReminderRequest request
    );
}
