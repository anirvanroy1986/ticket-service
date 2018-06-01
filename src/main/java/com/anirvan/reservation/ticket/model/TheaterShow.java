/**
 * 
 */
package com.anirvan.reservation.ticket.model;

import java.util.Map;

/**
 * @author anirvanroy
 *
 */
public class TheaterShow {
	
	private String showName;
	private String showTime;
	private Map<Seat, String> seatMap;
	/**
	 * @return the showName
	 */
	public String getShowName() {
		return showName;
	}
	/**
	 * @param showName the showName to set
	 */
	public void setShowName(String showName) {
		this.showName = showName;
	}
	/**
	 * @return the showTime
	 */
	public String getShowTime() {
		return showTime;
	}
	/**
	 * @param showTime the showTime to set
	 */
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	/**
	 * @return the seatMap
	 */
	public Map<Seat, String> getSeatMap() {
		return seatMap;
	}
	/**
	 * @param seatMap the seatMap to set
	 */
	public void setSeatMap(Map<Seat, String> seatMap) {
		this.seatMap = seatMap;
	}

}
