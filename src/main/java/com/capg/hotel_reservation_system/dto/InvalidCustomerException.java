package com.capg.hotel_reservation_system.dto;

public class InvalidCustomerException extends Exception{
	@Override
	public String getMessage() {
		return ("Invalid customer !! Exception thrown");
	}
}
