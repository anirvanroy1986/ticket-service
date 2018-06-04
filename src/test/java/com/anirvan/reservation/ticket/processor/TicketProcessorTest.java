/**
 * 
 */
package com.anirvan.reservation.ticket.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.anirvan.reservation.ticket.model.Seat;
import com.anirvan.reservation.ticket.model.SeatHold;
import com.anirvan.reservation.ticket.model.TheaterShow;
import com.anirvan.reservation.ticket.model.Venue;

/**
 * @author anirvanroy
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TicketProcessorTest {
	
	@InjectMocks
	TicketProcessor ticketProcessor;
	
	@Mock
    Venue venue; 
	
	@Mock
    TheaterShow show;
	
	@Before
    public void initObjects() {
		ticketProcessor = Mockito.spy(new TicketProcessor());
		ReflectionTestUtils.setField(ticketProcessor, "venue", new Venue());
		ReflectionTestUtils.setField(ticketProcessor, "rows", String.valueOf(15));
		ReflectionTestUtils.setField(ticketProcessor, "columns", String.valueOf(25));
		ReflectionTestUtils.setField(venue, "show", new TheaterShow());
	    MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testGetAvailableSeats() {
		Map<String,List<Seat>> seatMap = ticketProcessor.getAvailableSeats();
		assertNotNull(seatMap);
	}
	
	@Test
	public void testGetAvailableSeatsWhenReserved() {
		Map<String,List<Seat>> seatMap = new LinkedHashMap<>();
		List<Seat> seatList = new ArrayList<>();
		seatMap.put("R1", seatList);
		show.setSeatMap(seatMap);
		Mockito.doReturn(show).when(venue).getShow();
		Map<String,List<Seat>> seatMap1 = ticketProcessor.getAvailableSeats();
		assertNotNull(seatMap1);
	}
	
	@Test
	public void testGetTemporaryHeldSeats() {
		Map<String,List<Seat>> seatMap = ticketProcessor.getTemporaryHeldSeats();
		assertNotNull(seatMap);
	}
	
	@Test
	public void testHoldSeats() {
		long timeStamp = System.currentTimeMillis();
		Mockito.doReturn(show).when(venue).getShow();
		SeatHold seatHold = ticketProcessor.holdSeats(5,"R1",timeStamp);
		assertNotNull(seatHold);
		assertEquals(5, seatHold.getSeats().size());
	}
	
	@Test
	public void testHoldSeatsMax() {
		long timeStamp = System.currentTimeMillis();
		Mockito.doReturn(show).when(venue).getShow();
		SeatHold seatHold = ticketProcessor.holdSeats(25,"R1",timeStamp);
		assertNotNull(seatHold);
		assertEquals(25, seatHold.getSeats().size());
	}

}
