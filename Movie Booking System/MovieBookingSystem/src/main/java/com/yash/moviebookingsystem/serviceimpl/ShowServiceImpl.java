package com.yash.moviebookingsystem.serviceimpl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.yash.moviebookingsystem.exception.InvalidInputException;
import com.yash.moviebookingsystem.exception.NullException;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Show;
import com.yash.moviebookingsystem.service.ScreenService;
import com.yash.moviebookingsystem.service.ShowService;

public class ShowServiceImpl implements ShowService {

	private ScreenService screenService = new ScreenServiceImpl();

	public ShowServiceImpl(ScreenService screenService) {
		this.screenService = screenService;
	}

	public ShowServiceImpl() {
	}

	public List<Show> createShows(String movieName, int numberOfShows, int gold, int silver, int premium) {
		boolean isMovieExist = false;
		List<Screen> screens = screenService.getAllScreens();
		checkForEmtyScreens(screens);
		List<Show> shows = null;
		for (Screen screen : screens) {
			if (screen.getMovie().getTitle().equalsIgnoreCase(movieName)) {
				isMovieExist = true;
				shows = addNumberOfShows(movieName, numberOfShows, gold, silver, premium);
				screen.setShows(shows);
				break;
			}
		}
		checkForMovieNameExistance(isMovieExist);
		screenService.updateScreens(screens);
		return shows;
	}

	private void checkForEmtyScreens(List<Screen> screens) {
		if (screens.isEmpty()) {
			System.out.println("No Screen added Yet");
			throw new NullException("Add Screen First");
		}
	}

	private List<Show> addNumberOfShows(String movieName, int numberOfShows, int gold, int silver, int premium) {
		List<Show> shows = new LinkedList<>();
		switch (numberOfShows) {
		case 1:
			shows.add(createShow("9:30 AM", movieName, gold, silver, premium));
			break;
		case 2:
			shows.add(createShow("11:00 AM", movieName, gold, silver, premium));
			shows.add(createShow("3:30 PM", movieName, gold, silver, premium));
			break;
		case 3:
			shows.add(createShow("10:00 AM", movieName, gold, silver, premium));
			shows.add(createShow("2:30 PM", movieName, gold, silver, premium));
			shows.add(createShow("5:50 PM", movieName, gold, silver, premium));
			break;
		case 4:
			shows.add(createShow("10:00 AM", movieName, gold, silver, premium));
			shows.add(createShow("2:30 PM", movieName, gold, silver, premium));
			shows.add(createShow("5:50 PM", movieName, gold, silver, premium));
			shows.add(createShow("10:00 PM", movieName, gold, silver, premium));
			break;
		default:
			System.out.println("Can not Add more than 4 Shows in A day");
		}
		return shows;
	}

	private void checkForMovieNameExistance(boolean isMovieExist) {
		if (!isMovieExist)
			throw new InvalidInputException("Invalid Input. Movie Not Found !");
	}

	private Show createShow(String time, String movieName, int gold, int silver, int premium) {
		Show show = new Show();
		show.setShowTime(time);
		show.setPrices(initializingShowPrices(gold, silver, premium));
		show.setSeats(initializingSeatingForShow(movieName));
		return show;
	}

	private Map<String, Integer> initializingShowPrices(int gold, int silver, int premium) {
		Map<String, Integer> prices = new LinkedHashMap<>();
		prices.put("gold", gold);
		prices.put("silver", silver);
		prices.put("premium", premium);
		return prices;
	}

	private Map<String, List<Row>> initializingSeatingForShow(String movieName) {
		Map<String, List<Row>> seatingForShow = null;
		List<Screen> screens = screenService.getAllScreens();
		for (Screen screen : screens) {
			if (screen.getMovie().getTitle().equalsIgnoreCase(movieName)) {
				seatingForShow = screen.getSeatingArrangement();
				break;
			}
		}
		return seatingForShow;
	}

	public List<Show> getShowsByMovieName(String movieName) {
		boolean isMovieExist = false;
		List<Show> shows=null;
		List<Screen> screens=screenService.getAllScreens();
		for (Screen screen : screens) {
			if(screen.getMovie().getTitle().equalsIgnoreCase(movieName)){
				isMovieExist=true;
				shows=screen.getShows();
			}
		}
		checkForMovieNameExistance(isMovieExist);
		return shows;
	}
}
