/**
 * 
 */
package com.anirvan.reservation.ticket.model;

/**
 * @author anirvanroy
 *
 */
public class Seat {
	
	private String seatNum;
	
	/**
	 * @return the seatNum
	 */
	public String getSeatNum() {
		return seatNum;
	}

	/**
	 * @param seatNum the seatNum to set
	 */
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	@Override
	public String toString() {
		return seatNum;
	}
	

}
