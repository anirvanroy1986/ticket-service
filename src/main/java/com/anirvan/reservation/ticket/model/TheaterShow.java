/**
 * 
 */
package com.anirvan.reservation.ticket.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author anirvanroy
 *
 */
@Service
public class TheaterShow {
	
	private String showName;
	private String showTime;
	private Map<String, List<Seat>> seatMap = new LinkedHashMap<>();
	private Map<String, List<Seat>> temporarHeldSeatMap = new LinkedHashMap<>();
	private int totalReservedSeats;
	private int totalHeldSeats;
	

	public TheaterShow() {}
	
	public TheaterShow(int rows) {
		seatMap = new LinkedHashMap<>();
		temporarHeldSeatMap = new LinkedHashMap<>();
		for(int i=0; i< rows; i++) {
			String key = "R" + (i+1);
			seatMap.put(key, new ArrayList<Seat>());
			temporarHeldSeatMap.put(key, new ArrayList<Seat>());
		}
		setTotalReservedSeats(0);
		setTotalHeldSeats(0);
	}
	
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
	public Map<String, List<Seat>> getSeatMap() {
		return seatMap;
	}
	/**
	 * @param seatMap the seatMap to set
	 */
	public void setSeatMap(Map<String, List<Seat>> seatMap) {
		this.seatMap = seatMap;
	}
	/**
	 * @return the temporarHeldSeatMap
	 */
	public Map<String, List<Seat>> getTemporarHeldSeatMap() {
		return temporarHeldSeatMap;
	}
	/**
	 * @param temporarHeldSeatMap the temporarHeldSeatMap to set
	 */
	public void setTemporarHeldSeatMap(Map<String, List<Seat>> temporarHeldSeatMap) {
		this.temporarHeldSeatMap = temporarHeldSeatMap;
	}

	/**
	 * @return the totalReservedSeats
	 */
	public int getTotalReservedSeats() {
		return totalReservedSeats;
	}

	/**
	 * @param totalReservedSeats the totalReservedSeats to set
	 */
	public void setTotalReservedSeats(int totalReservedSeats) {
		this.totalReservedSeats = totalReservedSeats;
	}

	/**
	 * @return the totalHeldSeats
	 */
	public int getTotalHeldSeats() {
		return totalHeldSeats;
	}

	/**
	 * @param totalHeldSeats the totalHeldSeats to set
	 */
	public void setTotalHeldSeats(int totalHeldSeats) {
		this.totalHeldSeats = totalHeldSeats;
	}
	

}
