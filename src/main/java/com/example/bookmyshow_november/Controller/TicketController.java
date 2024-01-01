package com.example.bookmyshow_november.Controller;

import com.example.bookmyshow_november.Service.TicketService;
import com.example.bookmyshow_november.dtos.BookTicketRequestDto;
import com.example.bookmyshow_november.dtos.BookTicketResponseDto;
import com.example.bookmyshow_november.exceptions.NotFoundException;
import com.example.bookmyshow_november.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/")
    public ResponseEntity<BookTicketResponseDto> bookTicket(@RequestBody BookTicketRequestDto RequestDto)
                                                                                    throws NotFoundException {
        BookTicketResponseDto responseDto = new BookTicketResponseDto();

        Ticket ticket = ticketService.bookTicket(RequestDto.getSeatIds(),
                RequestDto.getUserId(), RequestDto.getShowId());
        responseDto.setTicketId(ticket.getId());
        responseDto.setAmount(ticket.getAmount());
        responseDto.setMovie(ticket.getShow().getMovie().getName());
        responseDto.setName(ticket.getBookedBy().getName());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
