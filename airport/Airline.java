package airport;

import java.io.Serializable;
import java.util.ArrayList;

public class Airline extends Airport implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getNameOfTheAirline() {
		return nameOfAnAirline;
	}
	public void setNameOfTheAirline(String nameOfTheAirline) {
		this.nameOfAnAirline = nameOfTheAirline;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public ArrayList<Airline> getAirlines() {
		return airlines;
	}
	public void setAirlines(ArrayList<Airline> airlines) {
		Airline.airlines = airlines;
	}

	static ArrayList<Airline> airlines = new ArrayList<Airline>();
	
	String nameOfAnAirline;
	String origin;
	String destination;
	
	public Airline(String nameOfAnAirline, String origin, String destination) {
		this.nameOfAnAirline = nameOfAnAirline;
		this.origin = origin;
		this.destination = destination;
	}
	
	public Airline() {
		
	}
	
}
