package com.example.eventBooking.service.imply;

import com.example.eventBooking.dto.request.ReminderRequest;
import com.example.eventBooking.dto.response.ReminderResponse;
import com.example.eventBooking.entity.Reminder;
import com.example.eventBooking.entity.User;
import com.example.eventBooking.exception.ResourceNotFoundException;
import com.example.eventBooking.repository.ReminderRepository;
import com.example.eventBooking.repository.UserRepository;
import com.example.eventBooking.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReminderServiceImply implements ReminderService {
    private final UserRepository userRepository;
    private final ReminderRepository reminderRepository;


    @Override
    @Transactional
    public ReminderResponse updateReminder(
            String email,
            ReminderRequest request) {

        // 1. Tìm user đang đăng nhập
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        // 2. Tìm Reminder, chưa có thì tạo mới
        Reminder reminder = reminderRepository
                .findByUser_Id(user.getId())
                .orElseGet(() -> {
                    Reminder newReminder = new Reminder();
                    newReminder.setUser(user);
                    newReminder.setCreatedAt(LocalDateTime.now());
                    return newReminder;
                });

        // 3. Cập nhật giá trị người dùng gửi lên
        reminder.setEventReminder(request.getEventReminder());

        // 4. Lưu database
        Reminder savedReminder = reminderRepository.save(reminder);

        // 5. Trả response
        return new ReminderResponse(savedReminder.getEventReminder());
    }
}