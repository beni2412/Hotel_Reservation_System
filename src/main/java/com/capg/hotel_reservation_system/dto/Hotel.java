package com.capg.hotel_reservation_system.dto;

public class Hotel {
	private String name;
	private int weekdayRate;
	private int weekendRate;

	public int getWeekendRate() {
		return weekendRate;
	}

	public void setWeekendRate(int weekendRate) {
		this.weekendRate = weekendRate;
	}

	public Hotel(String name, int weekdayRate) {
		super();
		this.name = name;
		this.weekdayRate = weekdayRate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeekdayRate() {
		return weekdayRate;
	}

	public void setWeekdayRate(int rate) {
		this.weekdayRate = rate;
	}

}
