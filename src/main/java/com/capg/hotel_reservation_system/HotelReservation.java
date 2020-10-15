package com.capg.hotel_reservation_system;

import java.time.DateTimeException;
import java.time.DayOfWeek;
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
		System.out.println("Enter check-in date: (YYYY-MM-DD) ");
		LocalDate dateIn = LocalDate.parse(sc.next());
		System.out.println("Enter check-out date: (YYYY-MM-DD) ");
		LocalDate dateOut = LocalDate.parse(sc.next());
		try {
			int cheapestPrice = findCheapestOffer(dateIn, dateOut);
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

	public static int findCheapestOffer(LocalDate dateIn, LocalDate dateOut) throws DateTimeException {
		
		int weekDays = noOfWeekDays(dateIn, dateOut);
		int weekEnds = noOfWeekendDays(dateIn, dateOut);
		int cheapestPrice=0;
		Hotel cheapestHotel = null;
		for (Hotel hotel : hotels.getListOfHotels()) {
			if (cheapestHotel == null) {
				cheapestHotel = hotel;
				cheapestPrice = (weekDays*cheapestHotel.getWeekdayRate())+(weekEnds*cheapestHotel.getWeekendRate());
			} else {
				int priceCheapest = (weekDays*cheapestHotel.getWeekdayRate())+(weekEnds*cheapestHotel.getWeekendRate());
				int priceThisHotel= (weekDays*hotel.getWeekdayRate())+(weekEnds*hotel.getWeekendRate());
				if(priceThisHotel<priceCheapest) {
					cheapestHotel=hotel;
					cheapestPrice = priceThisHotel;
				}
				}
			}
		System.out.println("Cheapest offers: ");
		for(Hotel hotel: hotels.getListOfHotels()) {
			int priceThisHotel= (weekDays*hotel.getWeekdayRate())+(weekEnds*hotel.getWeekendRate());
			if(priceThisHotel==cheapestPrice)
		System.out.println(hotel.getName() + ", Total Rates: $" +cheapestPrice );
		}
		
		return cheapestPrice;
	}

	public static int noOfWeekDays(LocalDate dateIn, LocalDate dateOut) {
		LocalDate date = dateIn;
		LocalDate forSundayCheck = LocalDate.parse("2020-10-18");
		LocalDate forSaturdayCheck = LocalDate.parse("2020-10-17");

		int countOfWeekdays = 0;
		do {
			DayOfWeek day = date.getDayOfWeek();
			if (day == forSaturdayCheck.getDayOfWeek() || day == forSundayCheck.getDayOfWeek()) {
				date = date.plusDays(1);
				continue;
			} else
				countOfWeekdays++;
			date = date.plusDays(1);

		} while (!(date.equals(dateOut)));
		return countOfWeekdays;
	}

	public static int noOfWeekendDays(LocalDate dateIn, LocalDate dateOut) {
		LocalDate date = dateIn;
		LocalDate forSundayCheck = LocalDate.parse("2020-10-18");
		LocalDate forSaturdayCheck = LocalDate.parse("2020-10-17");

		int countOfWeekendDays = 0;
		do {
			DayOfWeek day = date.getDayOfWeek();
			if (day == forSaturdayCheck.getDayOfWeek() || day == forSundayCheck.getDayOfWeek()) {
				countOfWeekendDays++;
			} else {
				date = date.plusDays(1);
				continue;
			}
			date = date.plusDays(1);

		} while (!(date.equals(dateOut)));
		return countOfWeekendDays;
	}
}
