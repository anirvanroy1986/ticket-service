package com.anirvan.reservation.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anirvan.reservation.ticket.model.SeatHold;
import com.anirvan.reservation.ticket.service.TicketService;

@SpringBootApplication
public class TicketServiceApplication implements CommandLineRunner{
	
	@Autowired
	TicketService ticketService;

	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Num of Seats available: "+ticketService.numSeatsAvailable());;
		SeatHold seatHold = ticketService.findAndHoldSeats(3, "");
		for(int i=0; i < seatHold.getSeats().size(); i++) {
			System.out.println(seatHold.getSeats().get(i).getSeatNum());;
		}
		System.out.println(seatHold.getConfirmationId());
		System.out.println(seatHold.getSeats().size());
		SeatHold seatHold2 = ticketService.findAndHoldSeats(5, "");
		for(int i=0; i < seatHold2.getSeats().size(); i++) {
			System.out.println(seatHold2.getSeats().get(i).getSeatNum());;
		}
	}
}
