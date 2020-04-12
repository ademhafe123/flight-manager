package airport;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static Scanner unos = new Scanner(System.in);
	static File flightFajl = new File("flight_manager.txt");
	static File airportFajl = new File("airport.txt");
	static File airlineFajl = new File("airline.txt");

	public static void main (String[] args) throws IOException {
	
		if(!flightFajl.exists()) {
			flightFajl.createNewFile();
		}
		if(!airlineFajl.exists()) {
			airlineFajl.createNewFile();
		}
		if(!airportFajl.exists()) {
			airportFajl.createNewFile();
		}
		try {
			spremanjeUListu();
		} catch (FileNotFoundException | ClassNotFoundException e) {
		}
		try {
			menu();
		}catch(InputMismatchException e) {
			System.out.println("You have to input a number! Try again: ");
			try {
				menu();
			} catch (IOException e1) {

			}
		}
		
		
		
	}
	
	public static void menu() throws IOException {
		System.out.println("1 Create an airport");
		System.out.println("2 Create an airline");
		System.out.println("3 Create a flight");
		System.out.println("4 Book a seat on a flight \n");
		
		int uneseniBroj = unos.nextInt();
		if(uneseniBroj > 0 && uneseniBroj < 5) {
			
		
			switch(uneseniBroj) {
			case 1:
				creatingAnAirport();
				break;
			case 2: 
				creatingAnAirline();
				break;
			case 3:
				creatingTheFlight();
				break;
			case 4:
				bookingASeat();
				break;
			}
		} else {
			System.out.println("Wrong input! Try again: ");
			menu();
		}
		try {
			spremanjeUFajl();
		} catch (IOException e) {
			}
	}
	
	
	
	
	
	
	public static void creatingAnAirport() throws IOException {
		System.out.println("Write a name for your Airport. Remember, name must have exactly 3 alpabhetic characters! \nExample: JFK. ");
		String nameOfAnAirport = unos.next();
		
		while(provjeraImenaAirporta(nameOfAnAirport) == false || provjeraSastavaImenaAirporta(nameOfAnAirport) == false) {
			System.out.println("Name must have exactly THREE ALPHABETICAL characters! Try again: ");
			nameOfAnAirport = unos.next();
		}
		
		while(provjeraPostojanjaImenaAirporta(nameOfAnAirport) == false) {
			System.out.println("This name is already taken. Try again: ");
			nameOfAnAirport = unos.next();
		}
		
		Airport airport = new Airport(nameOfAnAirport);
		Airport.airports.add(airport);
		
		System.out.println("You created an airport! \n");
		spremanjeUFajl();
		menu();
		
	}
	
	public static boolean provjeraImenaAirporta(String nameOfAnAirport) {	
		if(nameOfAnAirport.length() != 3) {
			return false;
			}
		return true;
		}
	
	public static boolean provjeraPostojanjaImenaAirporta(String nameOfAnAirport) {
		for(int i = 0; i < Airport.airports.size(); i++) {
			if(nameOfAnAirport.equals(Airport.airports.get(i).nameOfAnAirport)) {
				return false;
			} 
		}
		return true;
	}
	
	public static boolean provjeraSastavaImenaAirporta(String nameOfAnAirport) {
		for(int i = 0; i < nameOfAnAirport.length(); i++) {
			if(!Character.isAlphabetic(nameOfAnAirport.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	
	
	public static void creatingAnAirline() throws IOException {
		System.out.println("Write a name for your airline. Name must have less than 6 alphabetic characters! \nExample: south");
		String nameOfAnAirline = unos.next();
		while(provjeraImenaAirlinea(nameOfAnAirline) == false || provjeraSastavaImenaAilinea(nameOfAnAirline) == false) {
			System.out.println("The name musta have LESS than 6 ALPHABETICAL characters! Try again: ");
			nameOfAnAirline = unos.next();
		}
		
		while(provjeraPostojanjaImenaAirlinea(nameOfAnAirline) == false) {
			System.out.println("This name is already taken! Try again: ");
			nameOfAnAirline = unos.next();
		}
		
		System.out.println("Write the origin of this airline: ");
		unos.nextLine();
		String origin = unos.nextLine();
		
		System.out.println("Write the destination of this airline: ");
		String destinaion = unos.nextLine();
		
		Airline airline = new Airline(nameOfAnAirline, origin, destinaion);
		Airline.airlines.add(airline);
		
		System.out.println("You created an airline! \n");
		spremanjeUFajl();
		menu();
	}

	public static boolean provjeraImenaAirlinea(String nameOfAnAirline) {
			if(nameOfAnAirline.length() > 6) {
				return false;
			} else {
				return true;
			}
	}

	public static boolean provjeraPostojanjaImenaAirlinea(String nameOfAnAirline) {
		for(int i = 0; i < Airline.airlines.size(); i++) {
			if(nameOfAnAirline.equals(Airline.airlines.get(i).getNameOfTheAirline())) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean provjeraSastavaImenaAilinea(String nameOfAnAirline) {
		for(int i = 0; i < nameOfAnAirline.length(); i++) {
			if(!Character.isAlphabetic(nameOfAnAirline.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	
	public static void creatingTheFlight() throws IOException {
		System.out.println("Please enter the following: \n");
		System.out.println("Enter the name of the flight: ");
		unos.nextLine();
		String nameOfTheFlight = unos.nextLine();
		while(provjeraPostojanjaFlighta(nameOfTheFlight) == false) {
			System.out.println("Flight with this name already exists! Try again: ");
			nameOfTheFlight = unos.nextLine();
		}
		
		System.out.println("\nEnter the name of an airport: ");		
		String nameOfAnAirport = unos.next();
		while(provjeraPostojanjaAirporta(nameOfAnAirport) == false) {
			System.out.println("Airport with this name does not exist! Try again: ");
			nameOfAnAirport = unos.next();
		}
		
		System.out.println("\nEnter the name of an airline: ");
		String nameOfAnAirline = unos.next();
		while(provjeraPostojanjaAirlinea(nameOfAnAirline) == false) {
			System.out.println("Airline with this name does not exist! Try again: ");
			nameOfAnAirport = unos.next();
		}
		
		System.out.println("This flight has this origin and destination: ");
		String origin = null; 
		String destination = null;
		for(int i = 0; i < Airline.airlines.size(); i++) {
			if(nameOfAnAirline.equals(Airline.airlines.get(i).nameOfAnAirline)) {
				origin = Airline.airlines.get(i).origin;
				destination = Airline.airlines.get(i).destination;
			}
		}
		System.out.println("\nOrigin: " + origin + "\nDestination: " + destination);
		
		System.out.println("How many rows will this flight have: ");
		int numberOfRows = unos.nextInt();
		System.out.println("How many seats will those rows have: ");
		int numberOfSeatsInRows = unos.nextInt();
		
		boolean[][] seats = new boolean[numberOfRows][numberOfSeatsInRows];
			
		Flight flight = new Flight(nameOfAnAirport, nameOfAnAirline, origin, destination, nameOfTheFlight, seats);
		Flight.flights.add(flight);	
	
		System.out.println("You created a flight! ");
		spremanjeUFajl();
		menu();
	}
	
	public static boolean provjeraPostojanjaFlighta(String nameOfTheFlight) {
		for(int i = 0; i < Flight.flights.size(); i++) {
			if(nameOfTheFlight.equals(Flight.flights.get(i).nameOfTheFlight)){
				return false;
			}
		}
		return true;
	}
	
	
	public static boolean provjeraPostojanjaAirporta(String nameOfAnAirport) {
		for(int i = 0; i < Airport.airports.size(); i++) {	
			if(nameOfAnAirport.equals(Airport.airports.get(i).nameOfAnAirport)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean provjeraPostojanjaAirlinea(String nameOfAnAirline) {
		for(int i = 0; i < Airline.airlines.size(); i++) {
			if(nameOfAnAirline.equals(Airline.airlines.get(i).nameOfAnAirline)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean[][] creatingSeats(int numerOfRows, int numberOfSeatsInRows, boolean[][] seats){
		for(int i = 0; i < numerOfRows; i++) {
			for(int j = 0; j < seats[i].length; i++) {
				seats[i][j] = true;
			}
		}
		return seats;
	}
	
	
	
	public static void bookingASeat() throws IOException {
		
		System.out.println("\n --BOOKING A SEAT-- \n");
		System.out.println("Enter the name of a flight: ");
		unos.nextLine();
		String nameOfTheFlight = unos.nextLine();
		while(provjeraPostojanjaFlighta(nameOfTheFlight) == true) {
			System.out.println("Flight with this name does not exist! Try again: ");
			nameOfTheFlight = unos.nextLine();
		}
		
		System.out.println("Enter the number of row, and the seat: ");
		System.out.println("Row: ");
		int row = unos.nextInt() - 1;
		
		System.out.println("Seat: ");
		int seat = unos.nextInt() - 1;
		try {
		if(Flight.flights.get(dobijanjePozicijeLeta(seat, row, nameOfTheFlight)).seats[row][seat] == false) {
			Flight.flights.get(dobijanjePozicijeLeta(seat, row, nameOfTheFlight)).seats[row][seat] = true;
			System.out.println("You booked your seat successfuly! ");
		} else {
			System.out.println("This seat is already booked. ");
		}
		}catch(IndexOutOfBoundsException e) {
			System.out.println("This flight does not have that many rows or seats!");
			menu();
		}
		
		Seats seats = new Seats(row, seat, Flight.flights.get(dobijanjePozicijeLeta(seat, row, nameOfTheFlight)).seats);
		Seats.allSeats.add(seats);
		
		spremanjeUFajl();
		menu();
	}
	
	public static int dobijanjePozicijeLeta(int seat, int row, String nameOfTheFlight){
		
		for(int i = 0; i < Flight.flights.size(); i++) {
			if(nameOfTheFlight.equals(Flight.flights.get(i).nameOfTheFlight)) {
				return i;
			}
		}
		return -1;
	}

	
	
	
	
	public static void spremanjeUListu() throws FileNotFoundException, ClassNotFoundException {
		
		FileInputStream fiFlight = new FileInputStream(flightFajl);
		
		try {
			ObjectInputStream oiFlight = new ObjectInputStream(fiFlight);
				while(true) {
					try {
						Flight.flights.add((Flight)(oiFlight.readObject()));
					} catch (EOFException e) {
						break;
					}
				}
			oiFlight.close();
		}catch (IOException e) {
	
		}
		
		FileInputStream fiAirport = new FileInputStream(airportFajl);
		try {
			ObjectInputStream oiAirport = new ObjectInputStream(fiAirport);
			while(true) {
			try {
					Airport.airports.add((Airport)(oiAirport.readObject()));
				}catch(EOFException e) {
					break;
				}
			}
			oiAirport.close();
		} catch (IOException e) {
			
		}
		
		FileInputStream fiAirline = new FileInputStream(airlineFajl);
		try {
			ObjectInputStream oiAirline = new ObjectInputStream(fiAirline);
			while(true) {
				try {	
					Airline.airlines.add((Airline)(oiAirline.readObject()));
				} catch(EOFException e) {	
					break;
				}
			}
			oiAirline.close();
		}catch (IOException e) {
			
		}
		
	}
	
	
	
	
	public static void spremanjeUFajl() throws IOException {
		
		FileOutputStream foFlight = new FileOutputStream(flightFajl);
		
		ObjectOutputStream ooFlight = new ObjectOutputStream(foFlight);
		
		
		for(int i = 0; i < Flight.flights.size(); i++) {
			try {
				ooFlight.writeObject(Flight.flights.get(i));
			}catch(EOFException e) {
				break;
			}
		}
		ooFlight.close();
	
		FileOutputStream foAirport = new FileOutputStream(airportFajl);
		ObjectOutputStream ooAirport = new ObjectOutputStream(foAirport);
		
		for(int i = 0; i < Airport.airports.size(); i++) {
			try {
				ooAirport.writeObject(Airport.airports.get(i));
			}catch(EOFException e) {
				break;
			}
		}
		ooAirport.close();
		
		FileOutputStream foAirline = new FileOutputStream(airlineFajl);
		ObjectOutputStream ooAirline = new ObjectOutputStream(foAirline);
		
		for(int i = 0; i < Airline.airlines.size(); i++) {
			try {
				ooAirline.writeObject(Airline.airlines.get(i));
			}catch(EOFException e) {
				break;
			}
		} 
		ooAirline.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
