package com.example.bookmyshow_november.Controller;

import com.example.bookmyshow_november.Service.ShowService;
import com.example.bookmyshow_november.dtos.ShowRequestDto;
import com.example.bookmyshow_november.dtos.ShowResponseDto;
import com.example.bookmyshow_november.exceptions.NotFoundException;
import com.example.bookmyshow_november.models.Auditorium;
import com.example.bookmyshow_november.models.Show;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/show")
public class ShowController {
    private ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }
    @GetMapping("/")
    public ResponseEntity<ShowResponseDto> fetchShow(@RequestBody ShowRequestDto showRequestDto) throws ParseException, NotFoundException {
        ShowResponseDto showResponseDto = new ShowResponseDto();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        String dateInString = showRequestDto.getDate();
        Date date = formatter.parse(dateInString);

        Map<String, List<Date>> showMap = new HashMap<>();
        List<Show> shows = showService.fetchMovies(showRequestDto.getMovieId(), date, showRequestDto.getCityId());
        for(Show show: shows){
            String theatre_name = show.getAuditorium().getTheatre().getName();
            if(!showMap.containsKey(theatre_name)){
                List<Date> lst = new ArrayList<>();
                lst.add(show.getStartTime());
                showMap.put(theatre_name, lst);
            }else{
                showMap.get(theatre_name).add(show.getStartTime());
            }
        }
        showResponseDto.setMovie(shows.get(0).getMovie().getName());
        showResponseDto.setShows(showMap);
        return new ResponseEntity<>(showResponseDto, HttpStatus.OK);
    }
}
