package com.example.bookmyshow_november.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class ShowSeatType extends BaseModel{
    @ManyToOne
    private Show show;
    private int price;
    @ManyToOne
    private SeatType seatType;

}
