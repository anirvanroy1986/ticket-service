/**
 * 
 */
package com.anirvan.reservation.ticket.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anirvan.reservation.ticket.model.Seat;
import com.anirvan.reservation.ticket.model.SeatHold;
import com.anirvan.reservation.ticket.model.TheaterShow;
import com.anirvan.reservation.ticket.model.Venue;
import com.anirvan.reservation.ticket.processor.TicketProcessor;
import com.anirvan.reservation.ticket.service.TicketService;

/**
 * @author anirvanroy
 *
 */
@Service
public class TicketServiceImpl implements TicketService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Venue venue;
	@Autowired
	private TicketProcessor processor;
	@Value("${theater.rows}")
	String rows;
	@Value("${theater.columns}")
	String columns;

	@Value("${theater.seat.hold}")
	private String holdTimer;

	@Override
	public int numSeatsAvailable() {
		venue.setTotalSeats(Integer.parseInt(rows) * Integer.parseInt(columns));
		TheaterShow showTime = venue.getShow();
		if (showTime == null) {
			showTime = new TheaterShow();
		}
		int reservedSeatCount = showTime.getTotalReservedSeats();
		int tempReservedSeatCount = showTime.getTotalHeldSeats();
		LOGGER.info("Total Seats in theater "+venue.getTotalSeats());
		return ((Integer.parseInt(rows) * Integer.parseInt(columns)) - (reservedSeatCount + tempReservedSeatCount));
	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		removeTemporaryHold();
		// Check if requested number of seats available
		int totaSeatsAvailable = numSeatsAvailable();
		LOGGER.info("Number of Seats " + totaSeatsAvailable);
		if (totaSeatsAvailable < numSeats) {
			System.out.println("Theater has only " + totaSeatsAvailable + "available ");
			return null;
		}

		TheaterShow showTime = venue.getShow();
		SeatHold seatHold = null;
		if (showTime == null) {
			showTime = new TheaterShow();
		}

		seatHold = findBestSeats(numSeats, seatHold);

		return seatHold;
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) {
		return processor.bookSeats(seatHoldId);
	}

	/**
	 * Method to remove the temporary hold placed over a theater Seat after x number
	 * of seconds
	 */
	private void removeTemporaryHold() {
		// Iterate Hashmap to remove hold from seats after given seconds

		for (Map.Entry<Integer, SeatHold> entry : processor.getSeatHoldMapper().entrySet()) {
			long currentTime = System.currentTimeMillis();
			long timeElapsed = currentTime - entry.getValue().getTimeStamp();

			if ((timeElapsed) / 1000 > Integer.parseInt(holdTimer)) {
				LOGGER.info("More than 120 seconds elapsed, removing hold on tickets");
				processor.getSeatHoldMapper().remove(entry.getKey());
			}
		}
		Map<String, List<Seat>> tempMap = new LinkedHashMap<>();
		// Iterate Hashmap to remove hold from seats after given seconds
		int count = venue.getShow().getTotalHeldSeats();
		for (Map.Entry<String, List<Seat>> entry : venue.getShow().getTemporarHeldSeatMap().entrySet()) {
			List<Seat> seatList = entry.getValue();
			List<Seat> tempList = new ArrayList<>();
			for (Seat seat : seatList) {
				long currentTime = System.currentTimeMillis();
				long timeElapsed = currentTime - seat.getTimeStamp();
				if (!((timeElapsed) / 1000 > Integer.parseInt(holdTimer))) {
					tempList.add(seat);
				}else{
					LOGGER.info("Hold test 2");
					count--;
				}
				
			}
			tempMap.put(entry.getKey(), tempList);
			//venue.getShow().getTemporarHeldSeatMap().put(entry.getKey(), seatList);
		}
		venue.getShow().setTotalHeldSeats(count);
		venue.getShow().setTemporarHeldSeatMap(tempMap);

	}

	/**
	 * Method to get a list of Available seats and Temporary Seats and select the best seats
	 * from the top of the theater row and 
	 */
	private SeatHold findBestSeats(int seatsForBooking, SeatHold seatHold) {
		Map<String, List<Seat>> reservedSeatMap = processor.getAvailableSeats();
		Map<String, List<Seat>> temporarySeatMap = processor.getTemporaryHeldSeats();

		// Iterate Map to find which row can have the number of seats be Held
		int tempCounter = seatsForBooking;
		int confId = 0;
		boolean tempIdGenerator = false;
		List<Seat> tempList = new ArrayList<>();
		for (Map.Entry<String, List<Seat>> entry : reservedSeatMap.entrySet()) {
			int numOfSeatsBooked = entry.getValue().size();
			List<Seat> temporaryHeldSeatList = temporarySeatMap.get(entry.getKey());
			int numOfAvailableSeatsForRow = Integer.parseInt(columns)
					- (numOfSeatsBooked + temporaryHeldSeatList.size());
			long timeStamp = System.currentTimeMillis();
			if (numOfAvailableSeatsForRow > seatsForBooking) {
				// Hold Tickets
				seatHold = processor.holdSeats(seatsForBooking, entry.getKey(), timeStamp);
				tempList.addAll(seatHold.getSeats());
				break;
			}else if(numOfAvailableSeatsForRow == 0) {
				continue;
			}
			else {
				if(tempCounter > numOfAvailableSeatsForRow) {
					seatHold = processor.holdSeats(numOfAvailableSeatsForRow, entry.getKey(), timeStamp);
				}else {
					seatHold = processor.holdSeats(tempCounter, entry.getKey(), timeStamp);
					tempList.addAll(seatHold.getSeats());
					break;
				}
				if(!tempIdGenerator) {
					confId = seatHold.getSeatHoldId();
					tempIdGenerator = true;
				}
				tempCounter -= numOfAvailableSeatsForRow;
				seatHold.setSeatHoldId(confId);
				tempList.addAll(seatHold.getSeats());
				continue;
				//}
				
			}
		}
		seatHold.setSeats(tempList);
		return seatHold;
	}

}
