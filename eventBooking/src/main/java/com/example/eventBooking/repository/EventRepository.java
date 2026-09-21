package com.example.eventBooking.repository;

import com.example.eventBooking.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface EventRepository extends JpaRepository<Event, Long> {

    // =========================
    // POPULAR
    // =========================

    @Query(value = """
            SELECT e.*
            FROM events e
            LEFT JOIN bookings b
                ON e.id = b.event_id
            GROUP BY e.id
            ORDER BY COUNT(b.id) DESC
            """,
            countQuery = """
            SELECT COUNT(*)
            FROM events
            """,
            nativeQuery = true)
    Page<Event> findPopular(Pageable pageable);


    // =========================
    // UPCOMING
    // =========================

    @Query("""
            SELECT e
            FROM Event e
            WHERE e.dateTime >= :now
            ORDER BY e.dateTime ASC
            """)
    Page<Event> findUpcoming(
            @Param("now") LocalDateTime now,
            Pageable pageable
    );


    // =========================
    // SEARCH
    // =========================

    @Query("""
            SELECT e
            FROM Event e
            WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(e.location) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(e.description) LIKE LOWER(CONCAT('%', :search, '%'))
            """)
    Page<Event> searchEvents(
            @Param("search") String search,
            Pageable pageable
    );


    // =========================
    // NEARBY
    // =========================

    @Query(value = """
            SELECT e.*
            FROM events e
            WHERE
                (
                    6371 * acos(
                        cos(radians(:userLat))
                        * cos(radians(e.latitude))
                        * cos(radians(e.longitude) - radians(:userLng))
                        + sin(radians(:userLat))
                        * sin(radians(e.latitude))
                    )
                ) < 50
            ORDER BY
                (
                    6371 * acos(
                        cos(radians(:userLat))
                        * cos(radians(e.latitude))
                        * cos(radians(e.longitude) - radians(:userLng))
                        + sin(radians(:userLat))
                        * sin(radians(e.latitude))
                    )
                ) ASC
            """,
            countQuery = """
            SELECT COUNT(*)
            FROM events e
            WHERE
                (
                    6371 * acos(
                        cos(radians(:userLat))
                        * cos(radians(e.latitude))
                        * cos(radians(e.longitude) - radians(:userLng))
                        + sin(radians(:userLat))
                        * sin(radians(e.latitude))
                    )
                ) < 50
            """,
            nativeQuery = true)
    Page<Event> findNearby(
            @Param("userLat") double userLat,
            @Param("userLng") double userLng,
            Pageable pageable
    );
}