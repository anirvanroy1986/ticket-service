package com.anirvan.reservation.ticket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anirvan.reservation.ticket.model.SeatHold;
import com.anirvan.reservation.ticket.service.TicketService;

@SpringBootApplication
public class TicketServiceApplication implements CommandLineRunner{
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	TicketService ticketService;

	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Num of Seats available: "+ticketService.numSeatsAvailable());;
		SeatHold seatHold = ticketService.findAndHoldSeats(26, "");
		for(int i=0; i < seatHold.getSeats().size(); i++) {
			System.out.println(seatHold.getSeats().get(i).getSeatNum());;
		}
		LOGGER.info("Temporary confirmation ID: "+seatHold.getSeatHoldId());
		LOGGER.info("Available Seats"+seatHold.getSeats().size());
		SeatHold seatHold2 = ticketService.findAndHoldSeats(5, "");
		for(int i=0; i < seatHold2.getSeats().size(); i++) {
			System.out.println(seatHold2.getSeats().get(i).getSeatNum());;
		}
		LOGGER.info("Num of Seats available: "+ticketService.numSeatsAvailable());;
	}
}
