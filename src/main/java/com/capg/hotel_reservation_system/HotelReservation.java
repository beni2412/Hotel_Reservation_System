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
		int weekDays = 0;
		int weekEnds = 0;
		int cheapestPrice = 0;
		for (;;) {
			try {
				System.out.println("Enter check-in date: (YYYY-MM-DD) ");
				LocalDate dateIn = LocalDate.parse(sc.next());
				System.out.println("Enter check-out date: (YYYY-MM-DD) ");
				LocalDate dateOut = LocalDate.parse(sc.next());
				weekDays = hotels.noOfWeekDays(dateIn, dateOut);
				weekEnds = hotels.noOfWeekendDays(dateIn, dateOut);
				cheapestPrice = hotels.findCheapestOffer(dateIn, dateOut);
			} catch (DateTimeException e) {
				System.out.println("Invalid Date");
			}
			if (cheapestPrice != 0)
				break;
		}
		while (true) {
			System.out.println("\nEnter which option you want to see:");
			System.out.println("1. Cheapest Hotel available for given dates");
			System.out.println("2. Cheapest best rated Hotel for given dates");
			System.out.println("3. Best Rated Hotel for given dates");
			System.out.println("4. Exit");
			int option;
			option = sc.nextInt();
			switch (option) {
			case 1:
				hotels.cheapestHotel(cheapestPrice, weekDays, weekEnds);
				break;
			case 2:
				Hotel cheapestBestRateHotel = hotels.cheapestBestRatedHotel(cheapestPrice, weekDays, weekEnds);
				System.out.println(cheapestBestRateHotel.getName() + ", Rating: " + cheapestBestRateHotel.getRating()
						+ " and Total Rate: $" + cheapestPrice);
				break;
			case 3:
				Hotel bestRatedHotel = hotels.bestRatedHotel();
				int bestRatedHotelPrice = bestRatedHotel.getPrice(weekDays, weekEnds);
				System.out.println(bestRatedHotel.getName() + ", Rating: " + bestRatedHotel.getRating()
						+ " and Total Rate: $" + bestRatedHotelPrice);
				break;

			case 4:
				System.out.println("Bye!");
				return;
			default:
				break;
			}
		}

	}
}
