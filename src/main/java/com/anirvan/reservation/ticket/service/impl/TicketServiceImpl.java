/**
 * 
 */
package com.anirvan.reservation.ticket.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anirvan.reservation.ticket.model.Seat;
import com.anirvan.reservation.ticket.model.SeatHold;
import com.anirvan.reservation.ticket.model.TheaterShow;
import com.anirvan.reservation.ticket.model.Venue;
import com.anirvan.reservation.ticket.service.TicketService;

/**
 * @author anirvanroy
 *
 */
@Service
public class TicketServiceImpl implements TicketService{

	private Seat seatsArray[][];
	private Map<Integer, SeatHold> venueMap;
	@Autowired
	private Venue venue;
	@Value("${theater.rows}")
	String rows;
	@Value("${theater.columns}")
	String columns;
	@Override
	public int numSeatsAvailable() {
		venue.setTotalSeats(Integer.parseInt(rows) * Integer.parseInt(columns));
		TheaterShow showTime = venue.getShow();
		if(showTime == null) {
			showTime = new TheaterShow();
		}
		int reservedSeatCount = showTime.getSeatMap() != null? showTime.getSeatMap().size() : 0;
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
