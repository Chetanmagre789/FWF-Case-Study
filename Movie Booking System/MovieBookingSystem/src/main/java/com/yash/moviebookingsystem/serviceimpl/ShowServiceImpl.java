package com.yash.moviebookingsystem.serviceimpl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.yash.moviebookingsystem.exception.InvalidInputException;
import com.yash.moviebookingsystem.exception.NullException;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Seat;
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

	/**
	 * this method will create shows for the given movie and set the price of
	 * the tickets
	 * 
	 * @param movieName
	 *            name of the movie
	 * @param numberOfShows
	 *            number of shows to create for the movie
	 * @param gold
	 *            price of gold class
	 * @param silver
	 *            price of silver class
	 * @param premium
	 *            price of premium class
	 */
	public List<Show> createShows(String movieName, int numberOfShows, int gold, int silver, int premium) {
		boolean isMovieExist = false;
		boolean isShowsCreated = false;
		List<Screen> screens = screenService.getAllScreens();
		checkForEmtyScreens(screens);
		List<Show> shows = null;
		for (Screen screen : screens) {
			if (screen.getMovie().getTitle().equalsIgnoreCase(movieName)) {
				isMovieExist = true;
				shows = addNumberOfShows(movieName, numberOfShows, gold, silver, premium);
				screen.setShows(shows);
				isShowsCreated = true;
				break;
			}
		}
		checkForMovieNameExistance(isMovieExist);
		checkForShowCreated(isShowsCreated);
		screenService.updateScreens(screens);
		return shows;
	}

	private void checkForShowCreated(boolean isShowsCreated) {
		if (isShowsCreated) {
			System.out.println("Shows created Successfully");
		}
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
		List<Show> shows = null;
		List<Screen> screens = screenService.getAllScreens();
		for (Screen screen : screens) {
			if (screen.getMovie().getTitle().equalsIgnoreCase(movieName)) {
				isMovieExist = true;
				shows = screen.getShows();
			}
		}
		checkForMovieNameExistance(isMovieExist);
		return shows;
	}

	/**
	 * this method will book tickets for given show according to given rowIndex
	 * and seat Number
	 * 
	 * @param movieName
	 *            name of the movie
	 * @param showTime
	 *            time of show of that movie
	 * @param rowIndex
	 *            rowIndex of seat
	 * @param seats
	 *            list of seat number to book them
	 */
	public boolean bookShowTicketForMovie(String movieName, String showTime, String rowIndex,
			List<Integer> seatsToBook) {
		List<Screen> screens = screenService.getAllScreens();
		for (Screen screen : screens) {
			if (screen.getMovie().getTitle().equalsIgnoreCase(movieName)) {
				List<Show> shows = screen.getShows();
				for (Show show : shows) {
					if (show.getShowTime().equalsIgnoreCase(showTime)) {
						Map<String, List<Row>> screenSeating = show.getSeats();
						for (Map.Entry<String, List<Row>> seating : screenSeating.entrySet()) {
							List<Row> rows = seating.getValue();
							for (Row row : rows) {
								if (row.getRowIndex().equalsIgnoreCase(rowIndex)) {
									List<Seat> seats = row.getSeatsInRow();
									for (Seat seat : seats) {
										for (Integer seatToBook : seatsToBook) {
											if (seat.getSeatNo() == seatToBook)
												seat.setAvailable(false);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		screenService.updateScreens(screens);
		return true;
	}
}
