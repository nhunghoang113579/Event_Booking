
package com.example.eventBooking.controller;

import com.example.eventBooking.dto.request.ReminderRequest;
import com.example.eventBooking.dto.request.UpdateProfileRequest;
import com.example.eventBooking.dto.response.ApiResponse;
import com.example.eventBooking.dto.response.ProfileResponse;
import com.example.eventBooking.dto.response.ReminderResponse;
import com.example.eventBooking.service.ReminderService;
import com.example.eventBooking.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ReminderService reminderService;

    @PutMapping("/reminders")
    public ResponseEntity<ApiResponse<ReminderResponse>>updateReminder(@Valid @RequestBody ReminderRequest request,Authentication authentication){
        ReminderResponse response = reminderService.updateReminder(
                authentication.getName(), request);

        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Reminder settings updated successfully",
                response
                )
        );
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<ProfileResponse>> updateProfile(
            @Valid @RequestBody UpdateProfileRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        ProfileResponse response =
                userService.updateProfile(email, request);

        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Profile updated successfully",
                response));
    }
}
