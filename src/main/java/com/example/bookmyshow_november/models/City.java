package com.example.bookmyshow_november.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.aop.target.LazyInitTargetSource;

import java.util.List;
@Getter
@Setter
@Entity
public class City extends BaseModel{
    @OneToMany(mappedBy = "city")
    private List<Theatre> theatres;
    private String name;
}
