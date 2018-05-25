package com.yash.moviebookingsystem.model;

import java.util.List;
import java.util.Map;

public class Show {
	private int id;
	private String showTime;
	private Map<String, Integer> prices;
	private Map<String, List<Row>> seats;

	public Show() {
	}

	public Show(int id, String showTime, Map<String, Integer> prices, Map<String, List<Row>> seats) {
		super();
		this.id = id;
		this.showTime = showTime;
		this.prices = prices;
		this.seats = seats;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public Map<String, Integer> getPrices() {
		return prices;
	}

	public void setPrices(Map<String, Integer> prices) {
		this.prices = prices;
	}

	public Map<String, List<Row>> getSeats() {
		return seats;
	}

	public void setSeats(Map<String, List<Row>> seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "Show [id=" + id + ", showTime=" + showTime + ", prices=" + prices + ", seats=" + seats + "]";
	}

}
