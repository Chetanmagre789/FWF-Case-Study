package com.yash.moviebookingsystem.service;

import java.util.List;

import com.yash.moviebookingsystem.model.Show;

public interface ShowService {
	
	List<Show> createShows(String movieName, int numberOfShows,int gold, int silver, int premium);

	List<Show> getShowsByMovieName(String movieName);

}
