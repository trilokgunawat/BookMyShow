package com.example.bookmyshow_november.repositories;

import com.example.bookmyshow_november.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
    Ticket save(Ticket ticket);

}
