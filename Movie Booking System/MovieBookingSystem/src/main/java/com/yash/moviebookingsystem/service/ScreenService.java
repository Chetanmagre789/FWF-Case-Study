package com.yash.moviebookingsystem.service;

import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;

public interface ScreenService {

	boolean addNewScreen(Screen screen);

	boolean addMovieToScreen(String screenName,Movie movie);

}
