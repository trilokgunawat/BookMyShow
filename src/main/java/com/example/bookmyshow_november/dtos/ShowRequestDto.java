package com.example.bookmyshow_november.dtos;

import com.example.bookmyshow_november.models.City;
import com.example.bookmyshow_november.models.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShowRequestDto {
    private Long cityId;
    private Long movieId;
    private String date;
}
