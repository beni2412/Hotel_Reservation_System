package com.capg.hotel_reservation_system.dto;

import java.util.ArrayList;
import java.util.List;

public class ListOfHotels {
	private List<Hotel> listOfHotels = new ArrayList<Hotel>();

	public List<Hotel> getListOfHotels() {
		return listOfHotels;
	}

	public void setListOfHotels(List<Hotel> listOfHotels) {
		this.listOfHotels = listOfHotels;
	}
	
}
