package airport;

import java.io.Serializable;
import java.util.ArrayList;

public class Flight extends Airline implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getNameOfTheFlight() {
		return nameOfTheFlight;
	}
	public void setNameOfTheFlight(String nameOfTheFlight) {
		this.nameOfTheFlight = nameOfTheFlight;
	}
	public boolean[][] getSeats() {
		return seats;
	}
	public void setSeats(boolean[][] seats) {
		this.seats = seats;
	}
	public ArrayList<Flight> getFlights() {
		return flights;
	}
	public void setFlights(ArrayList<Flight> flights) {
		Flight.flights = flights;
	}
	
	String nameOfTheFlight;
	boolean[][] seats;
	
	static ArrayList<Flight> flights = new ArrayList<Flight>();
	
	public Flight(String nameOfTheFlight, boolean[][] seats) {
		this.nameOfTheFlight = nameOfTheFlight;
		this.seats = seats;
	}
	
	public Flight() {
		
	}
	
	public Flight(String nameOfAnAirport, String nameOfAnAirline, String origin, String destination, String nameOfTheFlight, boolean[][] seats) {
		this.nameOfAnAirport = nameOfAnAirport;
		this.nameOfAnAirline = nameOfAnAirline;
		this.origin = origin;
		this.destination = destination;
		this.nameOfTheFlight = nameOfTheFlight;
		this.seats = seats;
	}
	
	
	

}
