package com.yash.moviebookingsystem.model;

public class Seat {
	
	private int seatNo;
	private boolean available;

	public Seat() {
	}

	public Seat(int seatNo, boolean available) {
		super();
		this.seatNo = seatNo;
		this.available = available;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
