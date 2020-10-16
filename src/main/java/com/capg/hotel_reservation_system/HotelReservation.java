package com.capg.hotel_reservation_system;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import com.capg.hotel_reservation_system.dto.Hotel;
import com.capg.hotel_reservation_system.dto.InvalidCustomerException;
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
		String customerType = "";
		for (;;) {
			try {
				System.out.println("Enter check-in date: (YYYY-MM-DD) ");
				LocalDate dateIn = LocalDate.parse(sc.next());
				System.out.println("Enter check-out date: (YYYY-MM-DD) ");
				LocalDate dateOut = LocalDate.parse(sc.next());

				for (;;) {
					try {
						System.out.println("Enter customer type (regular/reward): ");
						customerType = sc.next();
						if (customerType.equalsIgnoreCase("reward") || customerType.equalsIgnoreCase("regular"))
							break;
						else
							throw new InvalidCustomerException();
					} catch (InvalidCustomerException e) {
						System.out.println(e.getMessage());
					}
				}

				weekDays = hotels.noOfWeekDays(dateIn, dateOut);
				weekEnds = hotels.noOfWeekendDays(dateIn, dateOut);
				cheapestPrice = hotels.findCheapestOffer(customerType, dateIn, dateOut);
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
				hotels.cheapestHotel(customerType, cheapestPrice, weekDays, weekEnds);
				break;
			case 2:
				Hotel cheapestBestRateHotel = hotels.cheapestBestRatedHotel(customerType, cheapestPrice, weekDays,
						weekEnds);
				System.out.println(cheapestBestRateHotel.getName() + ", Rating: " + cheapestBestRateHotel.getRating()
						+ " and Total Rate: $" + cheapestPrice);
				break;
			case 3:
				Hotel bestRatedHotel = hotels.bestRatedHotel();
				int bestRatedHotelPrice = customerType.equalsIgnoreCase("regular") ? bestRatedHotel.getPrice(weekDays, weekEnds)
						: bestRatedHotel.getRewardCustomerPrice(weekDays, weekEnds);
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
