package com.example.bookmyshow_november.Service;

import com.example.bookmyshow_november.exceptions.NotFoundException;
import com.example.bookmyshow_november.models.Movie;
import com.example.bookmyshow_november.models.Show;
import com.example.bookmyshow_november.models.ShowSeat;
import com.example.bookmyshow_november.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    private ShowRepository showRepository;
    @Autowired

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }


    public List<Show> fetchMovies(Long movieId, Date date, Long cityId) throws NotFoundException {
        Optional<List<Show>> shows = showRepository.findAllByDate(date, movieId, cityId);
        if(shows.isEmpty()){
            throw new NotFoundException("Show doesn't exist");
        }
        return shows.get();
    }
}
