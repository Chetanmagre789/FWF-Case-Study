package com.yash.moviebookingsystem.model;

import java.util.List;

public class Row {

	private int id;
	private String rowIndex;
	private List<Seat> seatsInRow;

	public Row() {
	}

	public Row(int id, String rowIndex, List<Seat> seatsInRow) {
		super();
		this.id = id;
		this.rowIndex = rowIndex;
		this.seatsInRow = seatsInRow;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(String rowIndex) {
		this.rowIndex = rowIndex;
	}

	public List<Seat> getSeatsInRow() {
		return seatsInRow;
	}

	public void setSeatsInRow(List<Seat> seatsInRow) {
		this.seatsInRow = seatsInRow;
	}

}
