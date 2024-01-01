package com.example.bookmyshow_november.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@ToString
public class BookTicketRequestDto implements Serializable {
    private List<Long> seatIds;
    private Long userId;
    private Long showId;

}
