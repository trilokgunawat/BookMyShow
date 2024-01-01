package com.example.bookmyshow_november.repositories;

import com.example.bookmyshow_november.models.Seat;
import com.example.bookmyshow_november.models.SeatType;
import com.example.bookmyshow_november.models.Show;
import com.example.bookmyshow_november.models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ShowSeat> findAllBySeatInAndShow(List<Seat> seats, Show show);

    ShowSeat save(ShowSeat showSeat);


}
