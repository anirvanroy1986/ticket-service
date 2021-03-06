/**
 * 
 */
package com.anirvan.reservation.ticket.processor;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anirvan.reservation.ticket.model.Seat;
import com.anirvan.reservation.ticket.model.SeatHold;
import com.anirvan.reservation.ticket.model.TheaterShow;
import com.anirvan.reservation.ticket.model.Venue;
import com.anirvan.reservation.ticket.utils.ConfirmationGenerator;

/**
 * Processor class that stores the Theater ticket reservation information and provides the necessary
 * information to the Service layer when needed.
 * @author anirvanroy
 *
 */
@Service
public class TicketProcessor {
	
	@Autowired
	private Venue venue;
	
	@Value("${theater.rows}")
	String rows;
	@Value("${theater.columns}")
	String columns;
	
	private Map<Integer, SeatHold> seatHoldMapper =  new HashMap<>();
	
	@PostConstruct
    public void init() {
        TheaterShow theaterShow = new TheaterShow(Integer.parseInt(rows));
        venue.setShow(theaterShow);
    }
	
	/**
	 * Gets a list of Reserved seats for the Theater
	 * @return
	 */
	public Map<String,List<Seat>> getAvailableSeats() {
		TheaterShow showTime = venue.getShow();
		if(showTime == null) {
			showTime = new TheaterShow();
		}
		
		return showTime.getSeatMap();
	}
	
	/**
	 * Gets a list of temporary held seats for the Theater
	 * @return
	 */
	public Map<String,List<Seat>> getTemporaryHeldSeats() {
		TheaterShow showTime = venue.getShow();
		if(showTime == null) {
			showTime = new TheaterShow();
		}
		return showTime.getTemporarHeldSeatMap();
	}
	
	/**
	 * Hold requested number of seats for a particular row. Generates the Timestamp, temporary confirmation code
	 * and returns the SeatHold object
	 * @param number
	 * @param row
	 * @param timeStamp
	 * @return SeatHold
	 */
	public synchronized SeatHold holdSeats(int number, String row, long timeStamp) {
		if(venue.getShow().getTemporarHeldSeatMap() == null) {
			venue.getShow().setTemporarHeldSeatMap(new HashMap<String, List<Seat>>());
		}
		List<Seat> seatListForRow = venue.getShow().getTemporarHeldSeatMap().get(row);
		
		List<Seat> temporaryHeldSeats = new ArrayList<>();
		//For first few seats to be added into the row
		if(seatListForRow == null) {
			List<Seat> seatList = new ArrayList<>();
			createSeats(number, seatList, 0, temporaryHeldSeats, row, timeStamp);
			venue.getShow().getTemporarHeldSeatMap().put(row, seatList);
		}else {
			int lastAssignedSeat = findLastAssignedSeatNumber(seatListForRow, venue.getShow().getSeatMap().get(row));
			createSeats(number, seatListForRow, lastAssignedSeat+1, temporaryHeldSeats, row, timeStamp);
			//seatListForRow.addAll(temporaryHeldSeats);
			venue.getShow().getTemporarHeldSeatMap().put(row, seatListForRow);
		}
		
		SeatHold seatHold = new SeatHold();
		seatHold.setSeats(temporaryHeldSeats);
		seatHold.setSeatHoldId(ConfirmationGenerator.getTemporaryConfirmationCode());
		seatHold.setTimeStamp(timeStamp);
		seatHoldMapper.put(new Integer(seatHold.getSeatHoldId()), seatHold);
		int revisedCount = temporaryHeldSeats.size() + venue.getShow().getTotalHeldSeats();
		venue.getShow().setTotalHeldSeats(revisedCount);
		return seatHold;
	}
	
	/**
	 * Commits the already held tickets to the Reserved seats map and returns a confirmation code
	 * @param seatConfId
	 * @return String
	 */
	public synchronized String bookSeats(int seatConfId) {
		SeatHold seatHold = seatHoldMapper.get(seatConfId);
		List<Seat> heldSeats = seatHold.getSeats();
		for(Seat seat: heldSeats) {
			String row = seat.getRow();
			venue.getShow().getSeatMap().get(row).add(seat);
			venue.getShow().getTemporarHeldSeatMap().get(row).remove(seat);
		}
		return ConfirmationGenerator.getConfirmationCode();
	}
	
	/**
	 * Finds the last assigned seat number for a row
	 * @param temporaryHeldSeats
	 * @param reservedSeats
	 * @return int
	 */
	private int findLastAssignedSeatNumber(List<Seat> temporaryHeldSeats, List<Seat> reservedSeats) {
		reservedSeats.addAll(temporaryHeldSeats);
		Collections.sort(reservedSeats);
		return reservedSeats.isEmpty()? 0 : reservedSeats.get(0).getId();
	}
	
	/**
	 * Helper method to create the Seat object
	 * @param size
	 * @param seatList
	 * @param seatAssignNum
	 * @param temporaryHeldSeatList
	 * @param row
	 * @param timeStamp
	 */
	private void createSeats(int size, List<Seat> seatList, int seatAssignNum, List<Seat> temporaryHeldSeatList, String row, long timeStamp) {
		
		for(int i=0; i < size; i++) {
			Seat seat = new Seat();
			seat.setSeatNum(row+"-"+String.valueOf(seatAssignNum+i));
			seat.setId(seatAssignNum+i);
			seat.setTimeStamp(timeStamp);
			seatList.add(seat);
			temporaryHeldSeatList.add(seat);
		}
	}

	/**
	 * @return the seatHoldMapper
	 */
	public Map<Integer, SeatHold> getSeatHoldMapper() {
		return seatHoldMapper;
	}

	/**
	 * @param seatHoldMapper the seatHoldMapper to set
	 */
	public void setSeatHoldMapper(Map<Integer, SeatHold> seatHoldMapper) {
		this.seatHoldMapper = seatHoldMapper;
	}
	

}
