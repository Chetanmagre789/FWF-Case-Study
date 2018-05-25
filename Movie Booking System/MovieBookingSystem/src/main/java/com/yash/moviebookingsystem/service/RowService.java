package com.yash.moviebookingsystem.service;

import java.util.List;

import com.yash.moviebookingsystem.model.Row;

public interface RowService {

	List<Row> designSeatingForClass(String seatingClass,int rowCount, int firstRowSeatCount);

}
