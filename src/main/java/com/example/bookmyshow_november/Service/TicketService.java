package com.example.bookmyshow_november.Service;

import com.example.bookmyshow_november.exceptions.NotFoundException;
import com.example.bookmyshow_november.models.*;
import com.example.bookmyshow_november.repositories.*;
import com.zaxxer.hikari.util.IsolationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional
public class TicketService {
    private SeatRepository seatRepository;
    private ShowSeatRepository showSeatRepository;
    private ShowRepository showRepository;
    private UserRepository userRepository;
    private ShowSeatTypeRepository showSeatTypeRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(SeatRepository seatRepository,
                         ShowSeatRepository showSeatRepository, ShowRepository showRepository,
                         TicketRepository ticketRepository, UserRepository userRepository, ShowSeatTypeRepository showSeatTypeRepository) {
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.showSeatTypeRepository = showSeatTypeRepository;
    }
    @Transactional
    public Ticket bookTicket(List<Long> seatIds, Long userId, Long showId) throws NotFoundException {
//        // for these seats Ids get the corresponding ShowSeats
//        //          ==> getSeatsForIds(Ids)
//        // check the status of seat for the showSeat
//        //          ==> getShowSeatForSeats(seats)
//        // if all seats are available -> lock every seat --> create ticket obj and return
//
//        // if any of the seat is not available --> throw an exception
//
//        // set transaction level as Serializable
//        // start transaction
        List<Seat> seats = seatRepository.findAllByIdIn(seatIds);
        if(seats.size() != seatIds.size()){
            throw new NotFoundException("seats are not properly selected");
        }

        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new NotFoundException("show by: " + showId + "doesn't exist.");
        }

        // checking if seats are available
        checkAvailabilityOfSeats(seats, showOptional);

        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new NotFoundException("user with id : " + userId + " doesn't exist.");
        }

        // calculate Price
        int ticketAmount = calculatePrice(showOptional.get(), seats);

        Ticket ticket = new Ticket();
        ticket.setBookedBy(userOptional.get());
        ticket.setStatus(TicketStatus.Processing);
        ticket.setShow(showOptional.get());
        ticket.setSeats(seats);
        ticket.setAmount(ticketAmount);
        ticket.setTimeOfBooking(new Date());

        Ticket savedTicket = ticketRepository.save(ticket);
        return savedTicket;
        // commit
        //unlock

    }
    public Integer calculatePrice(Show show, List<Seat> seats){
        int cost = 0;
        for(Seat seat : seats){
            SeatType type = seat.getSeatType();
            int seatPrice = showSeatTypeRepository.findPriceBySeatTypeAndShow(type, show).getPrice();
            cost += seatPrice;
        }
        return cost;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeat> checkAvailabilityOfSeats(List<Seat> seats, Optional<Show> showOptional) throws NotFoundException {

        List<ShowSeat> showSeats = showSeatRepository.findAllBySeatInAndShow(seats, showOptional.get());
        for(ShowSeat showSeat : showSeats){
            if ( showSeat.getStatus().equals(ShowSeatStatus.Booked)  ) {
                throw new NotFoundException("Select some other seats combination");
            }if( showSeat.getStatus().equals(ShowSeatStatus.Locked) &&
                    ( (new Date().getTime() -  showSeat.getLockedAt().getTime())/60 < 15  )){
                throw new NotFoundException("Select some other seats combination");
            }
        }
        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for(ShowSeat showSeat: showSeats){
            showSeat.setStatus(ShowSeatStatus.Locked);
            showSeat.setLockedAt(new Date());
            savedShowSeats.add(showSeatRepository.save(showSeat));

        }
        return savedShowSeats;

    }
}


