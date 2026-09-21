package com.example.eventBooking.repository;

import com.example.eventBooking.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.LinkOption;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
