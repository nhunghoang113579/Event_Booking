package com.example.eventBooking.repository;

import com.example.eventBooking.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.LinkOption;
import java.util.Optional;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    Optional<Reminder> findByUser_Id(Long userId);
}
