package com.example.bookmyshow_november.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookTicketResponseDto {
    private int amount;
    private Long ticketId;
    private List<String> seatNumber;
    private String auditoriumName;
    private String movie;
    private String name;
}
