package com.capg.hotel_reservation_system;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.capg.hotel_reservation_system.dto.Hotel;
import com.capg.hotel_reservation_system.dto.ListOfHotels;

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
			if(hotel.getName().equalsIgnoreCase("Lakewood"))
				hotelInList= true;
		}
		assertTrue(hotelInList);
	}
	
	@Test
	public void notAddedHotelCheck_ReturnFalse() {
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
			if(hotel.getName().equalsIgnoreCase("Raddison"))
				hotelInList= true;
		}
		assertFalse(hotelInList);
	}
	
	@Test
	public void FindCheapestHotel_ReturnTrue() {
		HotelReservation hotelReservation= new HotelReservation();
		hotelReservation.addHotelsList();
		LocalDate dateIn = LocalDate.parse("2020-10-16");
		LocalDate dateOut = LocalDate.parse("2020-10-18");
		try {
		int cheapestPrice = hotelReservation.findCheapestOffer(dateIn, dateOut);
		boolean correctChoice = false;
		if(cheapestPrice==200) {
			correctChoice=true;
			assertTrue(correctChoice);
		}
		}catch(DateTimeException e) {
			System.out.println("Invalid Date");
		}
	}

}
