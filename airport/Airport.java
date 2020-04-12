package airport;

import java.io.Serializable;
import java.util.ArrayList;

public class Airport implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public String getNameOfTheFlight() {
		return nameOfAnAirport;
	}

	public void setNameOfTheFlight(String nameOfTheFlight) {
		this.nameOfAnAirport = nameOfTheFlight;
	}

	public ArrayList<Airport> getAirport() {
		return airports;
	}

	public void setAirport(ArrayList<Airport> airport) {
		Airport.airports = airport;
	}

	
	static ArrayList<Airport> airports = new ArrayList<Airport>();
	
	String nameOfAnAirport;
	
	
	public Airport(String nameOfAnAirport) {
		this.nameOfAnAirport = nameOfAnAirport;
	}
	
	public Airport() {
		
	}
	
}
