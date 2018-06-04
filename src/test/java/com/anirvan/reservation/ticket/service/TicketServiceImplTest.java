/**
 * 
 */
package com.anirvan.reservation.ticket.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.test.util.ReflectionTestUtils;

import com.anirvan.reservation.ticket.model.Seat;
import com.anirvan.reservation.ticket.model.SeatHold;
import com.anirvan.reservation.ticket.model.TheaterShow;
import com.anirvan.reservation.ticket.model.Venue;
import com.anirvan.reservation.ticket.processor.TicketProcessor;
import com.anirvan.reservation.ticket.service.impl.TicketServiceImpl;

/**
 * @author anirvanroy
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {
	
	@InjectMocks
	TicketServiceImpl ticketService;
	@Mock
    Venue venue; 
	
	@Mock
    TheaterShow show;
	
	@Mock
	TicketProcessor processor;

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 
	
	@Before
    public void initObjects() {
		ticketService = Mockito.spy(new TicketServiceImpl());
		ReflectionTestUtils.setField(ticketService, "venue", new Venue());
		ReflectionTestUtils.setField(ticketService, "rows", String.valueOf(15));
		ReflectionTestUtils.setField(ticketService, "columns", String.valueOf(25));
		ReflectionTestUtils.setField(ticketService, "processor", new TicketProcessor());
		ReflectionTestUtils.setField(ticketService, "holdTimer", String.valueOf(120));
		ReflectionTestUtils.setField(venue, "show", new TheaterShow(15));
		ReflectionTestUtils.setField(processor, "venue", new Venue());
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
    public void testNumSeatsAvailableEmptyTheater() {
        int ans = ticketService.numSeatsAvailable();
        assertEquals(25 * 15, ans);
    }
	
	@Test
    public void testFindAndHoldSeats() {
		long timeStamp = System.currentTimeMillis();
		when(venue.getShow()).thenReturn(new TheaterShow());
		Mockito.doReturn(mockMaps(15)).when(processor).getAvailableSeats();
		Mockito.doReturn(mockMaps(15)).when(processor).getTemporaryHeldSeats();
		Mockito.doReturn(mockSeatHold()).when(processor).holdSeats(10, "R1",timeStamp);
		SeatHold seatHold = ticketService.findAndHoldSeats(10, "");
		assertEquals(1005, seatHold.getSeatHoldId());
    }
	
	@Test
    public void testFindAndHoldSeatsMaxCount() {
		
		when(venue.getShow()).thenReturn(new TheaterShow());
		SeatHold seatHold = ticketService.findAndHoldSeats(400, "");
		assertNull(seatHold);
    }
	
	@Test
    public void testHoldRemoval() throws InterruptedException {
		
		when(venue.getShow()).thenReturn(new TheaterShow());
		Map<Integer, SeatHold> testSeatHoldMapper = new HashMap<>();
		SeatHold seatHold1 = new SeatHold();
		long timeStamp = System.currentTimeMillis();
		seatHold1.setTimeStamp(timeStamp);
		Thread.sleep(121000);
		testSeatHoldMapper.put(new Integer(10001), seatHold1);
		Mockito.doReturn(testSeatHoldMapper).when(processor).getSeatHoldMapper();
		Mockito.doReturn(mockMaps(15)).when(processor).getAvailableSeats();
		Mockito.doReturn(mockMaps(15)).when(processor).getTemporaryHeldSeats();
		
		//long timeStamp = System.currentTimeMillis();
		Mockito.doReturn(mockSeatHold()).when(processor).holdSeats(10, "R1",timeStamp);
		SeatHold seatHold = ticketService.findAndHoldSeats(10, "");
		assertEquals(1005, seatHold.getSeatHoldId());
    }
	
	private Map<String,List<Seat>> mockMaps(int rows) {
		 Map<String, List<Seat>> seatMap = new LinkedHashMap<>();
		 Map<String, List<Seat>> temporarHeldSeatMap = new LinkedHashMap<>();
		for(int i=0; i< rows; i++) {
			String key = "R" + (i+1);
			seatMap.put(key, new ArrayList<Seat>());
			temporarHeldSeatMap.put(key, new ArrayList<Seat>());
		}
		return seatMap;
	}
	
	private SeatHold mockSeatHold() {
		SeatHold seatHold = new SeatHold();
		seatHold.setSeatHoldId(1005);
		return seatHold;
	}
	
	

}
