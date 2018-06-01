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
	private String timeStamp;
	private Hold hold;
	
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

	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return seatNum;
	}

	/**
	 * @return the hold
	 */
	public Hold getHold() {
		return hold;
	}

	/**
	 * @param hold the hold to set
	 */
	public void setHold(Hold hold) {
		this.hold = hold;
	}
	

}
