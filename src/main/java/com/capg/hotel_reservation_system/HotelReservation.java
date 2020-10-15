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
		hotels.addHotelsList();
		System.out.println("Enter check-in date: (YYYY-MM-DD) ");
		LocalDate dateIn = LocalDate.parse(sc.next());
		System.out.println("Enter check-out date: (YYYY-MM-DD) ");
		LocalDate dateOut = LocalDate.parse(sc.next());
		try {
			int cheapestPrice = hotels.findCheapestOffer(dateIn, dateOut);
		} catch (DateTimeException e) {
			System.out.println("Invalid Date");
		}
	}
}
