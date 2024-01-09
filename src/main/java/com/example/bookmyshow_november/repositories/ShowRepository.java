package com.example.bookmyshow_november.repositories;

import com.example.bookmyshow_november.models.Auditorium;
import com.example.bookmyshow_november.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    Optional<Show> findById(Long aLong);
    @Query("Select e from Event e where  date(e.startTime) = ?1 and e.movie.id = ?2 and e.city.id = ?3"
            )
    Optional<List<Show>> findAllByDate(Date date,Long movieId, Long cityId);
}
