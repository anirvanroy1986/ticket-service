/**
 * 
 */
package com.anirvan.reservation.ticket.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.util.ReflectionTestUtils;

import com.anirvan.reservation.ticket.model.Venue;
import com.anirvan.reservation.ticket.service.impl.TicketServiceImpl;

/**
 * @author anirvanroy
 *
 */
public class TicketServiceImplTest {
	
	TicketService ticketService;
	@Mock
    Venue venue; 

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 
	
	@Before
    public void initObjects() {
		ticketService = new TicketServiceImpl();
		ReflectionTestUtils.setField(ticketService, "venue", new Venue());
		ReflectionTestUtils.setField(ticketService, "rows", String.valueOf(15));
		ReflectionTestUtils.setField(ticketService, "columns", String.valueOf(25));
		
	}
	
	@Test
    public void testNumSeatsAvailableEmptyTheater() {
        int ans = ticketService.numSeatsAvailable();
        assertEquals(25 * 15, ans);
    }
	
	

}
