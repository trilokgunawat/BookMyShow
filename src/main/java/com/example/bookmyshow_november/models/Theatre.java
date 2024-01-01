package com.example.bookmyshow_november.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{
    @ManyToOne
    private City city;
    private String name;
    @OneToMany
    List<Auditorium> auditoriums;
    private String address;

}
