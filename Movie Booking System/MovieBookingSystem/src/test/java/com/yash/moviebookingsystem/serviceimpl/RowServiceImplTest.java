package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yash.moviebookingsystem.exception.InvalidDesignInputException;
import com.yash.moviebookingsystem.service.RowService;

public class RowServiceImplTest {

	private RowService rowService = new RowServiceImpl();

	@Test(expected = InvalidDesignInputException.class)
	public void designSeatingForClass_WhenClassAndTotalNumberOfRowAndInvalidFirstRowSeatCountIsGiven_ThrowInvalidInputException() {
		rowService.designSeatingForClass("silver", 2, 0);
	}

	@Test(expected = InvalidDesignInputException.class)
	public void designSeatingForClass_WhenClassAndInvalidTotalNumberOfRowAndFirstRowSeatCountIsGiven_ThrowInvalidInputException() {
		rowService.designSeatingForClass("silver", 0, 0);
	}

	@Test(expected = InvalidDesignInputException.class)
	public void designSeatingForClass_WhenInvalidClassAndTotalNumberOfRowAndFirstRowSeatCountIsGiven_ThrowInvalidInputException() {
		rowService.designSeatingForClass("Temp", 2, 4);
	}

	@Test
	public void designSeatingForClass_WhenClassAndTotalNumberOfRowAndFirstRowSeatCountIsGiven_ShouldReturnListOfRows() {
		assertEquals(4, rowService.designSeatingForClass("silver", 4, 8).size());
	}

}
