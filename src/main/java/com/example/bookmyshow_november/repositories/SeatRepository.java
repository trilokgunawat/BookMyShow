package com.example.bookmyshow_november.repositories;

import com.example.bookmyshow_november.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findAllByIdIn(List<Long> seatIds);
}
