package com.yash.moviebookingsystem.model;

import java.util.List;
import java.util.Map;

public class Screen {

	private String screenName;
	private Movie movie;
	private Map<String, List<Row>> seatingArrangement;
	private Map<String, Map<String, List<Row>>> shows;

	public Screen() {
	}

	public Screen(String screenName) {
		this.screenName = screenName;
	}

	public Screen(String screenName, Movie movie, Map<String, List<Row>> seatingArrangement,
			Map<String, Map<String, List<Row>>> shows) {
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

	public Map<String, Map<String, List<Row>>> getShows() {
		return shows;
	}

	public void setShows(Map<String, Map<String, List<Row>>> shows) {
		this.shows = shows;
	}

	@Override
	public String toString() {
		return "Screen [screenName=" + screenName + ", movie=" + movie + ", seatingArrangement=" + seatingArrangement
				+ ", shows=" + shows + "]";
	}

}
