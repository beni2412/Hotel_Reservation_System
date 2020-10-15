package com.capg.hotel_reservation_system;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import com.capg.hotel_reservation_system.dto.Hotel;
import com.capg.hotel_reservation_system.dto.ListOfHotels;

public class HotelReservation {
	static Scanner sc = new Scanner(System.in);
	static ListOfHotels hotels = new ListOfHotels();

	public static void main(String[] args) {

		System.out.println("Welcome to Hotel Reservation Program");
		addHotelsList();
		try {
			Hotel cheapestHotel = findCheapestOffer();
		} catch (DateTimeException e) {
			System.out.println("Invalid Date");
		}

	}

	public static void addHotelsList() {
		Hotel lakewood = new Hotel("Lakewood", 110);
		Hotel bridgewood = new Hotel("Bridgewood", 150);
		Hotel ridgewood = new Hotel("Ridgewood", 220);
		lakewood.setWeekendRate(90);
		bridgewood.setWeekendRate(50);
		ridgewood.setWeekendRate(150);
		hotels.addHotel(lakewood);
		hotels.addHotel(bridgewood);
		hotels.addHotel(ridgewood);
		
	}

	public static Hotel findCheapestOffer() throws DateTimeException {
		System.out.println("Enter check-in date: (YYYY-MM-DD) ");
		LocalDate dateIn = LocalDate.parse(sc.next());
		System.out.println("Enter check-out date: (YYYY-MM-DD) ");
		LocalDate dateOut = LocalDate.parse(sc.next());
		Period period = Period.between(dateIn, dateOut);
		int days = period.getDays();
		Hotel cheapestHotel = null;
		for (Hotel cheapest : hotels.getListOfHotels()) {
			if (cheapestHotel == null) {
				cheapestHotel = cheapest;
			} else {
				if (cheapestHotel.getWeekdayRate() > cheapest.getWeekdayRate()) {
					cheapestHotel = cheapest;
				}
			}
		}
		System.out.println("The cheapest offer is: ");
		System.out.println(cheapestHotel.getName() + ", Total Rates: $" + cheapestHotel.getWeekdayRate() * days);
		return cheapestHotel;
	}
}
