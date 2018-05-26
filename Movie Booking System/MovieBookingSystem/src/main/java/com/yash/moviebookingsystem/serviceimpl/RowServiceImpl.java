package com.yash.moviebookingsystem.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.yash.moviebookingsystem.exception.InvalidDesignInputException;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Seat;
import com.yash.moviebookingsystem.service.RowService;

public class RowServiceImpl implements RowService {

	/**
	 * this method will design seating for given seatingClass such as Gold,
	 * Silver, Premium along with the given rowCount i.e total number of rows in
	 * class and number of Seats in 1st row
	 * 
	 * @param seatingClass
	 *            the class for which designing seating
	 * @param rowCount
	 *            total number of rows to design
	 * @param firstRowSeatCount
	 *            total number of seats in 1st row to design
	 */
	public List<Row> designSeatingForClass(String seatingClass, int rowCount, int firstRowSeatCount) {
		checkForInvalidDesignInput(rowCount, firstRowSeatCount);
		List<Row> rowSeatingForClass = new ArrayList<>();
		String initialIndex = initalizeRowIndexforGivenClass(seatingClass);
		for (int i = 1; i <= rowCount; i++) {
			Row row = new Row();
			row.setRowIndex(initialIndex + i);
			List<Seat> seats = creatingSeatsForRow(firstRowSeatCount);
			firstRowSeatCount -= 2;
			row.setSeatsInRow(seats);
			rowSeatingForClass.add(row);
		}
		return rowSeatingForClass;
	}

	private void checkForInvalidDesignInput(int rowCount, int firstRowSeatCount) {
		if (firstRowSeatCount < (rowCount * 2) || rowCount == 0) {
			throw new InvalidDesignInputException(
					"Minimum number of seat in 1st row to make Seating should be greater or equal to " + rowCount * 2
							+ "\n");
		}
		if (rowCount > 10) {
			throw new InvalidDesignInputException("Row Should Not be More Than 10");
		}
	}

	private List<Seat> creatingSeatsForRow(int firstRowSeatCount) {
		List<Seat> seats = new ArrayList<>();
		for (int j = 1; j <= firstRowSeatCount; j++) {
			Seat seat = new Seat();
			seat.setSeatNo(j);
			seat.setAvailable(true);
			seats.add(seat);
		}
		return seats;
	}

	private String initalizeRowIndexforGivenClass(String seatingClass) {
		String initialIndex = null;
		switch (seatingClass.toLowerCase()) {
		case "gold":
			initialIndex = "G";
			break;
		case "silver":
			initialIndex = "S";
			break;
		case "premium":
			initialIndex = "P";
			break;
		default:
			throw new InvalidDesignInputException("Invalid Class Name valid Names are Gold, Silver, Premium");
		}
		return initialIndex;
	}

}
