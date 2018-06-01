/**
 * 
 */
package com.anirvan.reservation.ticket.service.impl;

import java.util.Map;

import com.anirvan.reservation.ticket.model.Seat;
import com.anirvan.reservation.ticket.model.SeatHold;
import com.anirvan.reservation.ticket.service.TicketService;

/**
 * @author anirvanroy
 *
 */
public class TicketServiceImpl implements TicketService{

	private Seat seatsArray[][];
	private Map<Integer, SeatHold> venueMap;
	@Override
	public int numSeatsAvailable() {
		
		return 0;
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
