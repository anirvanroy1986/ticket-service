/**
 * 
 */
package com.anirvan.reservation.ticket.model;

/**
 * @author anirvanroy
 *
 */
public class Hold {
	
	private String timeStamp;
	private boolean indicator;
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
	/**
	 * @return the indicator
	 */
	public boolean isIndicator() {
		return indicator;
	}
	/**
	 * @param indicator the indicator to set
	 */
	public void setIndicator(boolean indicator) {
		this.indicator = indicator;
	}

}
