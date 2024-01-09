package com.example.bookmyshow_november.dtos;

import com.example.bookmyshow_november.models.Auditorium;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ShowResponseDto {
    private String movie;
    private Map<String, List<Date>> shows;

}
