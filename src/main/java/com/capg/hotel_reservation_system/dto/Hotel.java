package com.capg.hotel_reservation_system.dto;

public class Hotel {
	private String name;
	private int weekdayRate;
	private int weekendRate;
	private int rating;
	private int rewardWeekdayRate;
	private int rewardWeekendRate;

	public int getRewardWeekdayRate() {
		return rewardWeekdayRate;
	}

	public void setRewardWeekdayRate(int rewardWeekdayRate) {
		this.rewardWeekdayRate = rewardWeekdayRate;
	}

	public int getRewardWeekendRate() {
		return rewardWeekendRate;
	}

	public void setRewardWeekendRate(int rewardWeekendRate) {
		this.rewardWeekendRate = rewardWeekendRate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

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

	public int getPrice(int weekDays, int weekEnds) {
		int price = (weekDays * this.getWeekdayRate()) + (weekEnds * this.getWeekendRate());
		return price;
	}

}
