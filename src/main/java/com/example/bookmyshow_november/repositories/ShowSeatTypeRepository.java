package com.example.bookmyshow_november.repositories;

import com.example.bookmyshow_november.models.SeatType;
import com.example.bookmyshow_november.models.Show;
import com.example.bookmyshow_november.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    ShowSeatType findPriceBySeatTypeAndShow(SeatType seatType, Show show);
//    ShowSeatType findPriceBySeatTypeAndShow
}
