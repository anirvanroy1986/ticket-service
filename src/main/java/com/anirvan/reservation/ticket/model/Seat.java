/**
 * 
 */
package com.anirvan.reservation.ticket.model;

import java.util.Objects;

/**
 * @author anirvanroy
 *
 */
public class Seat implements Comparable<Seat>{
	
	private String seatNum;
	private long timeStamp;
	
	private int id;
	private String row;
	
	/**
	 * @return the seatNum
	 */
	public String getSeatNum() {
		return seatNum;
	}

	/**
	 * @param seatNum the seatNum to set
	 */
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	/**
	 * @return the timeStamp
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return seatNum;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof SeatHold)) {
            return false;
        }
        Seat seats = (Seat) o;
        return 
                Objects.equals(seatNum, seats.getSeatNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatNum);
    }

	@Override
	public int compareTo(Seat o) {
	   int compareSeatNo=(o).getId();
	   /* For Descending order*/
	   return compareSeatNo - this.id;
	}

	/**
	 * @return the row
	 */
	public String getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(String row) {
		this.row = row;
	}
	

}
