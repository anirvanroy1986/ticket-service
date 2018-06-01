/**
 * 
 */
package com.anirvan.reservation.ticket.model;

import java.util.List;
import java.util.Objects;

/**
 * @author anirvanroy
 *
 */
public class SeatHold {
	
	private String confirmationId;
	private List<Seat> seats;
	private int seatHoldId;
	private long timeStamp;
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
	/**
	 * @return the seatHoldId
	 */
	public int getSeatHoldId() {
		return seatHoldId;
	}
	/**
	 * @param seatHoldId the seatHoldId to set
	 */
	public void setSeatHoldId(int seatHoldId) {
		this.seatHoldId = seatHoldId;
	}
	
	 @Override
	    public boolean equals(Object o) {

	        if (o == this) return true;
	        if (!(o instanceof SeatHold)) {
	            return false;
	        }
	        SeatHold seats = (SeatHold) o;
	        return seatHoldId == seats.getSeatHoldId() &&
	                Objects.equals(confirmationId, seats.confirmationId);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(seatHoldId);
	    }
		/**
		 * @return the timeStamp
		 */
		public long getTimeStamp() {
			return timeStamp;
		}
		/**
		 * @param timeStamp the timeStamp to set
		 */
		public void setTimeStamp(long timeStamp) {
			this.timeStamp = timeStamp;
		}

}
