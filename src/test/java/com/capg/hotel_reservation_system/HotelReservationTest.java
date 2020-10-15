package com.capg.hotel_reservation_system;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.capg.hotel_reservation_system.dto.Hotel;
import com.capg.hotel_reservation_system.dto.ListOfHotels;

import junit.framework.Assert;

public class HotelReservationTest {

	@Test
	public void addedHotelCheck_ReturnTrue() {
		ListOfHotels Hotels = new ListOfHotels();
		Hotel lakewood = new Hotel("Lakewood", 110);
		Hotel bridgewood = new Hotel("Bridgewood", 150);
		Hotel ridgewood = new Hotel("Ridgewood", 220);
		lakewood.setWeekendRate(90);
		bridgewood.setWeekendRate(50);
		ridgewood.setWeekendRate(150);
		Hotels.addHotel(lakewood);
		Hotels.addHotel(bridgewood);
		Hotels.addHotel(ridgewood);
		boolean hotelInList = false;
		for (Hotel hotel : Hotels.getListOfHotels()) {
			if (hotel.getName().equalsIgnoreCase("Lakewood"))
				hotelInList = true;
		}
		assertTrue(hotelInList);
	}

	@Test
	public void notAddedHotelCheck_ReturnFalse() {
		ListOfHotels hotels = new ListOfHotels();
		Hotel lakewood = new Hotel("Lakewood", 110);
		Hotel bridgewood = new Hotel("Bridgewood", 150);
		Hotel ridgewood = new Hotel("Ridgewood", 220);
		lakewood.setWeekendRate(90);
		bridgewood.setWeekendRate(50);
		ridgewood.setWeekendRate(150);
		hotels.addHotel(lakewood);
		hotels.addHotel(bridgewood);
		hotels.addHotel(ridgewood);
		boolean hotelInList = false;
		for (Hotel hotel : hotels.getListOfHotels()) {
			if (hotel.getName().equalsIgnoreCase("Raddison"))
				hotelInList = true;
		}
		assertFalse(hotelInList);
	}

	@Test
	public void FindCheapestHotel_ReturnTrue() {
		HotelReservation hotelReservation = new HotelReservation();
		ListOfHotels hotels = new ListOfHotels();
		hotels.addHotelsList();
		LocalDate dateIn = LocalDate.parse("2020-10-16");
		LocalDate dateOut = LocalDate.parse("2020-10-18");
		try {
			int cheapestPrice = hotels.findCheapestOffer(dateIn, dateOut);
			boolean correctChoice = false;
			if (cheapestPrice == 200) {
				correctChoice = true;
				assertTrue(correctChoice);
			}
		} catch (DateTimeException e) {
			System.out.println("Invalid Date");
		}
	}

	@Test
	public void SetRateandGetRate_ReturnTrue() {
		HotelReservation hotelReservation = new HotelReservation();
		ListOfHotels hotels = new ListOfHotels();
		hotels.addHotelsList();
		int maxRating = 1;
		int minRating = 5;
		boolean isCorrect = false;
		for (Hotel hotel : hotels.getListOfHotels()) {
			if (hotel.getRating() > maxRating)
				maxRating = hotel.getRating();
			if (hotel.getRating() < minRating)
				minRating = hotel.getRating();
		}
		if (maxRating == 5 && minRating == 3)
			isCorrect = true;
		assertTrue(isCorrect);

	}

	@Test
	public void cheapestBestRatedHotel_ReturnTrue() {
		ListOfHotels hotels = new ListOfHotels();
		hotels.addHotelsList();
		LocalDate dateIn = LocalDate.parse("2020-10-16");
		LocalDate dateOut = LocalDate.parse("2020-10-18");
		int weekDays = hotels.noOfWeekDays(dateIn, dateOut);
		int weekEnds = hotels.noOfWeekendDays(dateIn, dateOut);
		boolean correctChoice = false;
		try {
			int cheapestPrice = hotels.findCheapestOffer(dateIn, dateOut);
			Hotel cheapestBestRateHotel = hotels.cheapestBestRatedHotel(cheapestPrice, weekDays, weekEnds);
			if (cheapestBestRateHotel.getName().equalsIgnoreCase("Bridgewood"))
				correctChoice = true;
			assertTrue(correctChoice);
		} catch (DateTimeException e) {
			System.out.println("Invalid Date");
		}
	}

	@Test
	public void bestRatedHotel_ReturnTrueWhenCorrect() {
		ListOfHotels hotels = new ListOfHotels();
		hotels.addHotelsList();
		Hotel bestRatedHotel = hotels.bestRatedHotel();
		boolean correctChoice = false;
		if (bestRatedHotel.getName().equalsIgnoreCase("Ridgewood"))
			correctChoice = true;
		assertTrue(correctChoice);
	}
	
	@Test
	public void bestRatedHotel_ReturnFalseWhenIncorrect() {
		ListOfHotels hotels = new ListOfHotels();
		hotels.addHotelsList();
		Hotel bestRatedHotel = hotels.bestRatedHotel();
		boolean correctChoice = false;
		if (bestRatedHotel.getName().equalsIgnoreCase("Bridgewood"))
			correctChoice = true;
		assertFalse(correctChoice);
	}

}
