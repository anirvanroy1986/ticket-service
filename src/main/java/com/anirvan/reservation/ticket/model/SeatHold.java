/**
 * 
 */
package com.anirvan.reservation.ticket.model;

import java.util.List;

/**
 * @author anirvanroy
 *
 */
public class SeatHold {
	
	private String confirmationId;
	private List<Seat> seats;
	/**
	 * @return the confirmationId
	 */
	public String getConfirmationId() {
		return confirmationId;
	}
	/**
	 * @param confirmationId the confirmationId to set
	 */
	public void setConfirmationId(String confirmationId) {
		this.confirmationId = confirmationId;
	}
	/**
	 * @return the seats
	 */
	public List<Seat> getSeats() {
		return seats;
	}
	/**
	 * @param seats the seats to set
	 */
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}
