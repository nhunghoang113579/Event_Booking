package com.example.eventBooking.repository;

import com.example.eventBooking.entity.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
