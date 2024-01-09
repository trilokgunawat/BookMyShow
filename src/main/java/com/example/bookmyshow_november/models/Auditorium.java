package com.example.bookmyshow_november.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Auditorium extends BaseModel{
    private String name;
    @OneToMany
    private List<Seat> seats;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
    @ManyToOne
//    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

}
