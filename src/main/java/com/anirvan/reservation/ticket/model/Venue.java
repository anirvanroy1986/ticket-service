/**
 * 
 */
package com.anirvan.reservation.ticket.model;

import java.util.Map;

/**
 * @author anirvanroy
 *
 */
public class Venue {
	
	private int totalSeats;
	private String name;
	private String venueId;
	private TheaterShow show;
	/**
	 * @return the totalSeats
	 */
	public int getTotalSeats() {
		return totalSeats;
	}
	/**
	 * @param totalSeats the totalSeats to set
	 */
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the venueId
	 */
	public String getVenueId() {
		return venueId;
	}
	/**
	 * @param venueId the venueId to set
	 */
	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}
	/**
	 * @return the show
	 */
	public TheaterShow getShow() {
		return show;
	}
	/**
	 * @param show the show to set
	 */
	public void setShow(TheaterShow show) {
		this.show = show;
	}

}
