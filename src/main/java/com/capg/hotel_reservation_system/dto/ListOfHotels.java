package com.capg.hotel_reservation_system.dto;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListOfHotels {
	private List<Hotel> listOfHotels = new ArrayList<Hotel>();

	public List<Hotel> getListOfHotels() {
		return listOfHotels;
	}

	public void setListOfHotels(List<Hotel> listOfHotels) {
		this.listOfHotels = listOfHotels;
	}

	public void addHotel(Hotel hotel) {
		listOfHotels.add(hotel);
	}

	public void addHotelsList() {
		Hotel lakewood = new Hotel("Lakewood", 110);
		Hotel bridgewood = new Hotel("Bridgewood", 150);
		Hotel ridgewood = new Hotel("Ridgewood", 220);
		lakewood.setWeekendRate(90);
		bridgewood.setWeekendRate(50);
		ridgewood.setWeekendRate(150);
		lakewood.setRating(3);
		bridgewood.setRating(4);
		ridgewood.setRating(5);
		lakewood.setRewardWeekdayRate(80);
		lakewood.setRewardWeekendRate(80);
		bridgewood.setRewardWeekdayRate(110);
		bridgewood.setRewardWeekendRate(50);
		ridgewood.setRewardWeekdayRate(100);
		ridgewood.setRewardWeekendRate(40);
		addHotel(lakewood);
		addHotel(bridgewood);
		addHotel(ridgewood);

	}

	public int findCheapestOffer(String customerType, LocalDate dateIn, LocalDate dateOut) throws DateTimeException {

		int weekDays = noOfWeekDays(dateIn, dateOut);
		int weekEnds = noOfWeekendDays(dateIn, dateOut);
		int cheapestPrice = 0;
		Hotel cheapestHotel = null;
		for (Hotel hotel : listOfHotels) {
			if (cheapestHotel == null) {
				cheapestHotel = hotel;
				cheapestPrice = customerType.equalsIgnoreCase("regular") ? hotel.getPrice(weekDays, weekEnds)
						: hotel.getRewardCustomerPrice(weekDays, weekEnds);
			} else {
				int priceThisHotel = customerType.equalsIgnoreCase("regular") ? hotel.getPrice(weekDays, weekEnds)
						: hotel.getRewardCustomerPrice(weekDays, weekEnds);
				if (priceThisHotel < cheapestPrice) {
					cheapestHotel = hotel;
					cheapestPrice = priceThisHotel;
				}
			}
		}

		return cheapestPrice;
	}

	public int noOfWeekDays(LocalDate dateIn, LocalDate dateOut) {
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

	public int noOfWeekendDays(LocalDate dateIn, LocalDate dateOut) {
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

	public Hotel cheapestBestRatedHotel(String customerType, int cheapestPrice, int weekDays, int weekEnds) {
		int rating = 0;
		Hotel cheapestBestRateHotel = null;
		 List<Hotel> listOfCheapestHotels = listOfHotels.stream().filter(hotel ->{
			int priceThisHotel = customerType.equalsIgnoreCase("regular") ? hotel.getPrice(weekDays, weekEnds)
					: hotel.getRewardCustomerPrice(weekDays, weekEnds);
			if (priceThisHotel == cheapestPrice)
				return true;
			else return false;
		}).sorted(Comparator.comparing(Hotel::getRating).reversed()).collect(Collectors.toList());
		 cheapestBestRateHotel = listOfCheapestHotels.get(0);
		
		return cheapestBestRateHotel;

	}

	public void cheapestHotel(String customerType, int price, int weekDays, int weekEnds) {
		System.out.println("Cheapest offers: ");
		listOfHotels.stream().forEach(hotel -> {
			int priceThisHotel = customerType.equalsIgnoreCase("regular") ? hotel.getPrice(weekDays, weekEnds)
					: hotel.getRewardCustomerPrice(weekDays, weekEnds);
			if (priceThisHotel == price)
				System.out.println(hotel.getName() + ", Total Rates: $" + price);
		});
		}

	public Hotel bestRatedHotel() {
		int rating = 0;
		Hotel bestRatedHotel = null;
		 List<Hotel> listHotels = listOfHotels.stream().sorted(Comparator.comparing(Hotel::getRating).reversed()).collect(Collectors.toList());
			 bestRatedHotel = listHotels.get(0);
		
		return bestRatedHotel;
	}
}
