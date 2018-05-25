package com.yash.moviebookingsystem.service;

import java.util.List;
import java.util.Map;

import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;

public interface ScreenService {

	List<Screen> getAllScreens();

	boolean addNewScreen(Screen screen);

	boolean addMovieToScreen(String screenName, Movie movie);

	boolean addSeatingsToScreen(Map<String, List<Row>> seatings, String screenName);

	void updateScreeens(List<Screen> screens);

}
