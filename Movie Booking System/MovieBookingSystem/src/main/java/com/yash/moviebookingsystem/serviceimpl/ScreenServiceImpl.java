package com.yash.moviebookingsystem.serviceimpl;

import java.util.List;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.NullException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.service.ScreenService;

public class ScreenServiceImpl implements ScreenService {

	private ScreenDAO screenDAO;

	public ScreenServiceImpl(ScreenDAO screenDAO) {
		this.screenDAO = screenDAO;
	}

	/**
	 * this method will add new screen
	 * 
	 * @param screen
	 *            screen object to be add
	 * @return return boolean value based on the given screen object
	 */
	public boolean addNewScreen(Screen screen) {
		boolean isAddable = false;
		checkForNullOrEmptyScreenName(screen);
		List<Screen> screens = screenDAO.getScreens();
		if (isScreenNameExist(screen, isAddable, screens)) {
			if (screens.size() >= 3) {
				isAddable = false;
				System.out.println("Can't add more than 3 Screens.");
			} else
				isAddable = screenDAO.addScreen(screen);
		} else {
			System.out.println("ScreenName already exist");
		}
		return isAddable;
	}

	private boolean isScreenNameExist(Screen screen, boolean isAdded, List<Screen> screens) {
		if (!screens.isEmpty())
			for (Screen screenInRepository : screens) {
				if (isScreenExist(screen, screenInRepository)) {
					isAdded = false;
					break;
				}
				isAdded = true;
			}
		return isAdded;
	}

	private boolean isScreenExist(Screen screen, Screen screenInRepository) {
		return screenInRepository.getScreenName().equalsIgnoreCase(screen.getScreenName());
	}

	private void checkForNullOrEmptyScreenName(Screen screen) {
		if (screen == null || screen.getScreenName().trim().equals("")) {
			throw new NullException("Can't add null screen or Empty screenName");
		}
	}

	/**
	 * this method will add movie to given screen
	 * 
	 * @param screenName
	 *            name of the screen in which movie will be added
	 * @param movie
	 *            movie object containing movie details such as title, duration,
	 *            production
	 */
	public boolean addMovieToScreen(String screenName, Movie movie) {
		checkForNullMovie(movie);
		boolean movieAddedStatus = false;
		List<Screen> screens = screenDAO.getScreens();
		for (Screen screen : screens) {
			if (screen.getSeatingArrangement() != null) {
				movieAddedStatus = isScreenExistToAddMovie(screenName, movie, movieAddedStatus, screen);
			} else {
				System.out.println("First design Seating for Given Screen : " + screenName);
				break;
			}
		}
		return movieAddedStatus;
	}

	private boolean isScreenExistToAddMovie(String screenName, Movie movie, boolean movieAddedStatus, Screen screen) {
		if (screen.getScreenName().equalsIgnoreCase(screenName)) {
			screen.setMovie(movie);
			movieAddedStatus = true;
		} else {
			System.out.println("Screen not found");
		}
		return movieAddedStatus;
	}

	private void checkForNullMovie(Movie movie) {
		if (movie == null) {
			throw new NullException("Null can not be added");
		}
	}

}
