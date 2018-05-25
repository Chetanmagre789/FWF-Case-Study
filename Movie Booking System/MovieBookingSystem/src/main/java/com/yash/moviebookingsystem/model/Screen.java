package com.yash.moviebookingsystem.model;

import java.util.List;
import java.util.Map;

public class Screen {

	private String screenName;
	private Movie movie;
	private Map<String, List<Row>> seatingArrangement;
	private List<Show> shows;

	public Screen() {
	}

	public Screen(String screenName) {
		this.screenName = screenName;
	}

	public Screen(String screenName, Movie movie, Map<String, List<Row>> seatingArrangement, List<Show> shows) {
		super();
		this.screenName = screenName;
		this.movie = movie;
		this.seatingArrangement = seatingArrangement;
		this.shows = shows;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Map<String, List<Row>> getSeatingArrangement() {
		return seatingArrangement;
	}

	public void setSeatingArrangement(Map<String, List<Row>> seatingArrangement) {
		this.seatingArrangement = seatingArrangement;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	@Override
	public String toString() {
		return "Screen [screenName=" + screenName + ", movie=" + movie + ", seatingArrangement=" + seatingArrangement
				+ ", shows=" + shows + "]";
	}

}
