/**
 * 
 */
package com.anirvan.reservation.ticket.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.anirvan.reservation.ticket.service.impl.TicketServiceImpl;

/**
 * @author anirvanroy
 *
 */
public class TicketServiceImplTest {
	
	TicketService ticketService;
	
	@Before
    public void initObjects() {
		ticketService = new TicketServiceImpl();
	}
	
	@Test
    public void testNumSeatsAvailableEmptyTheater() {
        int ans = ticketService.numSeatsAvailable();
        assertEquals(9, ans);
    }
	
	@Test
    public void testNumSeatsAvailable() {
        int ans = ticketService.numSeatsAvailable();
        assertEquals(9, ans);
    }

}
