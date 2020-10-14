package com.capg.hotel_reservation_system;

import com.capg.hotel_reservation_system.dto.Hotel;
import com.capg.hotel_reservation_system.dto.ListOfHotels;

public class HotelReservation {
	static ListOfHotels Hotels = new ListOfHotels();
	public static void main(String[] args) {
		
		System.out.println("Welcome to Hotel Reservation Program");
		addHotelsList();
	}
	public static void addHotelsList() {
		Hotels.addHotel(new Hotel("Lakewood", 110));
		Hotels.addHotel(new Hotel("Bridgewood", 150));
		Hotels.addHotel(new Hotel("Ridgewood", 220));
	}
}
