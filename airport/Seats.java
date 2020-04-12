package airport;

import java.io.Serializable;
import java.util.ArrayList;

public class Seats implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getNumberOfRows() {
		return row;
	}
	public void setNumberOfRows(int numberOfRows) {
		this.row = numberOfRows;
	}
	public int getNumberOfSeatsInEachRow() {
		return seat;
	}
	public void setNumberOfSeatsInEachRow(int numberOfSeatsInEachRow) {
		this.seat = numberOfSeatsInEachRow;
	}
	public boolean[][] getSeats() {
		return seats;
	}
	public void setSeats(boolean[][] seats) {
		Seats.seats = seats;
	}
	
	static ArrayList<Seats> allSeats = new ArrayList<Seats>();
	int row;
	int seat;
	static boolean[][] seats;
	
	
	public Seats(int row, int seat, boolean[][] seats) {
		this.row = row;
		this.seat = seat;
		Seats.seats = seats;
	}
	public Seats() {
		
	}
}
