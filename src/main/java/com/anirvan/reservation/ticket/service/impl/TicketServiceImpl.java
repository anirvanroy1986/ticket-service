/**
 * 
 */
package com.anirvan.reservation.ticket.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.anirvan.reservation.ticket.model.Seat;
import com.anirvan.reservation.ticket.model.SeatHold;
import com.anirvan.reservation.ticket.model.TheaterShow;
import com.anirvan.reservation.ticket.model.Venue;
import com.anirvan.reservation.ticket.service.TicketService;

/**
 * @author anirvanroy
 *
 */
public class TicketServiceImpl implements TicketService{

	private Seat seatsArray[][];
	private Map<Integer, SeatHold> venueMap;
	private Venue venue;
	@Value(value = "theater.rows")
	int rows;
	@Value(value = "theater.columns")
	int columns;
	@Override
	public int numSeatsAvailable() {
		venue.setTotalSeats(rows * columns);
		TheaterShow showTime = venue.getShow();
		int reservedSeatCount = showTime.getSeatMap().size();
		return (venue.getTotalSeats() - reservedSeatCount);
	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) {
		// TODO Auto-generated method stub
		return null;
	}

}
