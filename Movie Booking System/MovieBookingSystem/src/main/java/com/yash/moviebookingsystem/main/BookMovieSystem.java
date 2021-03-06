package com.yash.moviebookingsystem.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.yash.moviebookingsystem.exception.InvalidDesignInputException;
import com.yash.moviebookingsystem.exception.InvalidInputException;
import com.yash.moviebookingsystem.exception.NullException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Seat;
import com.yash.moviebookingsystem.model.Show;
import com.yash.moviebookingsystem.service.RowService;
import com.yash.moviebookingsystem.service.ScreenService;
import com.yash.moviebookingsystem.service.ShowService;
import com.yash.moviebookingsystem.serviceimpl.RowServiceImpl;
import com.yash.moviebookingsystem.serviceimpl.ScreenServiceImpl;
import com.yash.moviebookingsystem.serviceimpl.ShowServiceImpl;

public class BookMovieSystem {
	private Logger logger = Logger.getLogger(BookMovieSystem.class);
	private ScreenService screenService = new ScreenServiceImpl();

	private RowService rowService = new RowServiceImpl();

	private ShowService showService = new ShowServiceImpl();

	private Scanner scanOperatorInput = new Scanner(System.in);

	public void displayOperatorMenu() {
		logger.info("Opertor Menu Method called");
		int operatorInput = 0;
		do {
			System.out.println("---------------- Movie Booking System ---------------");
			System.out.println(
					"1. Add Screen \n2. Add Seating Arrangement To Screen \n3. Add Movie To Screen \n4. Add Shows For Movie \n5. Check Available Shows \n6. Book Show Ticket for Movie \n0. Exit \n");
			System.out.println("Enter Your Choice :-");
			operatorInput = scanOperatorInput.nextInt();
			switch (operatorInput) {
			case 1:
				addScreenOption();
				break;
			case 2:
				addSeatingArrangementToScreenOption();
				break;
			case 3:
				addMovieToScreenOption();
				break;
			case 4:
				addShowForMovieOption();
				break;
			case 5:
				checkAvailableShowsOption();
				break;
			case 6:
				bookShowTicketForMovieOption();
				break;
			case 0:
				System.out.println("\nThank you for Using Movie Booking System.\n");
				System.exit(0);
				break;
			default:
				System.out.println("Poor choice for option. Please Select Valid Option \n");
				break;
			}
		} while (true);
	}

	private void bookShowTicketForMovieOption() {
		boolean isMovie = false;
		int count = 0;
		boolean isShow = false;
		List<Show> shows =null;
		List<Screen> screens = screenService.getAllScreens();
		for (Screen screen : screens) {
			if (screen.getMovie() != null) {
				System.out.println(screen.getMovie().getTitle());
				count++;
			} else
				System.out.println("No Movie in : " + screen.getScreenName());
		}
		if (count != 0) {
			System.out.println("Enter Movie Name from Above List : ");
			scanOperatorInput.nextLine();
			String movieName = scanOperatorInput.nextLine();
			for (Screen screen : screens) {
				if (screen.getMovie().getTitle().equalsIgnoreCase(movieName)) {
					isMovie = true;
					shows = screen.getShows();
					if (shows == null) {
						System.out.println("Add Shows for The Movie First");
					} else {
						for (Show show : shows) {
							System.out.print(show.getShowTime() + "  ");
							isShow = true;
						}
						System.out.println();
					}
				}
			}
			if (!isMovie) {
				System.out.println("Invalid Movie name");
			}
			if (isShow) {
				System.out.println("Enter Show Time from Above list : ");
				String showTime=scanOperatorInput.nextLine();
				showSeatings(shows, showTime);
				System.out.println("Enter Row Number : ");
				String rowIndex=scanOperatorInput.nextLine();
				List<Integer> seats=new ArrayList<>();
				String stop=null;
				do {
					System.out.println("Enter Seat Number : ");
					seats.add(scanOperatorInput.nextInt());
					System.out.println("book More Seat (Y/N)");
					stop = scanOperatorInput.next();
				} while (stop.equalsIgnoreCase("Y"));
				showService.bookShowTicketForMovie(movieName, showTime, rowIndex, seats);
			}		
			
		} else {
			System.out.println("Add Movie First");
		}

	}

	private void checkAvailableShowsOption() {
		System.out.println("Enter Movie name :");
		scanOperatorInput.nextLine();
		String movieName = scanOperatorInput.nextLine();
		List<Show> shows = showService.getShowsByMovieName(movieName);
		for (Show show : shows) {
			System.out.print(show.getShowTime() + "   ");
		}
		System.out.println();
		System.out.println("Enter Show time To Check Seats :");
		String timeOfShow = scanOperatorInput.nextLine();
		showSeatings(shows, timeOfShow);
	}

	private void showSeatings(List<Show> shows, String timeOfShow) {
		for (Show show : shows) {
			if (show.getShowTime().equalsIgnoreCase(timeOfShow)) {
				System.out.println("---------------Screen This Side--------------------");
				Map<String, List<Row>> screenSeating = show.getSeats();
				for (Map.Entry<String, List<Row>> seating : screenSeating.entrySet()) {
					System.out.println(seating.getKey() + " Class");
					List<Row> rows = seating.getValue();
					int space = 1;
					for (Row row : rows) {
						space++;
						List<Seat> seats = row.getSeatsInRow();
						System.out.print(row.getRowIndex() + "\t");
						for (int i = 0; i < space; i++) {
							System.out.print(" ");
						}
						for (Seat seat : seats) {
							if (seat.isAvailable())
								System.out.print(seat.getSeatNo() + " ");
							else
								System.out.print("X ");
						}
						System.out.println();
					}
					space = 0;
				}
			}
		}
	}

	private void addShowForMovieOption() {
		System.out.println("Enter Movie Name :");
		scanOperatorInput.nextLine();
		String movieName = scanOperatorInput.nextLine();
		System.out.println("Enter Number of Shows In a Day :");
		int numberOfShows = scanOperatorInput.nextInt();
		System.out.println("Enter Price for GOLD Class Seat : ");
		int gold = scanOperatorInput.nextInt();
		System.out.println("Enter Price for SILVER Class Seat : ");
		int silver = scanOperatorInput.nextInt();
		System.out.println("Enter Price for PREMIUM Class Seat : ");
		int premium = scanOperatorInput.nextInt();
		try {
			showService.createShows(movieName, numberOfShows, gold, silver, premium);
		} catch (InvalidInputException | NullException exception) {
			logger.error(exception.getMessage());
		}

	}

	private void addMovieToScreenOption() {
		boolean isAdded;
		System.out.println("Enter Screen Name : ");
		scanOperatorInput.nextLine();
		String screenName = scanOperatorInput.nextLine();
		Movie movie = new Movie();
		System.out.println("Enter Movie name : ");
		movie.setTitle(scanOperatorInput.nextLine());
		System.out.println("Enter Production : ");
		movie.setProduction(scanOperatorInput.nextLine());
		String stop = null;
		List<String> actors = new ArrayList<>();
		do {
			System.out.println("Enter Actors : ");
			actors.add(scanOperatorInput.nextLine());
			System.out.println("Add More Actor (Y/N)");
			stop = scanOperatorInput.nextLine();
		} while (stop.equalsIgnoreCase("Y"));
		movie.setActors(actors);
		System.out.println("Enter Movie Duration in HH:mm format : ");
		movie.setDuration(scanOperatorInput.nextLine());
		try {
			isAdded = screenService.addMovieToScreen(screenName, movie);
			if (!isAdded) {
				System.out.println("Screen Found: Invalid Screen Name");
			}
		} catch (NullException nullException) {
			logger.error(nullException.getMessage());
		}
	}

	private void addSeatingArrangementToScreenOption() {
		Map<String, List<Row>> seating = new LinkedHashMap<>();
		String screenName = null;
		int rowCount;
		int initialSeatCount;
		boolean isAdded = false;
		try {
			System.out.println("Enter Screen Name : ");
			scanOperatorInput.nextLine();
			screenName = scanOperatorInput.nextLine();
			System.out.println("Enter Total Number of row in PREMIUM Class maximum 10 Rows");
			rowCount = scanOperatorInput.nextInt();
			System.out.println("Enter Number of seat in 1st Row");
			initialSeatCount = scanOperatorInput.nextInt();
			List<Row> seats = rowService.designSeatingForClass("premium", rowCount, initialSeatCount);
			seating.put("premium", seats);
			System.out.println("Enter Total Number of row in SILVER Class maximum 10 Rows");
			rowCount = scanOperatorInput.nextInt();
			System.out.println("Enter Number of seat in 1st Row");
			initialSeatCount = scanOperatorInput.nextInt();
			seats = rowService.designSeatingForClass("silver", rowCount, initialSeatCount);
			seating.put("silver", seats);
			System.out.println("Enter Total Number of row in GOLD Class maximum 10 Rows");
			rowCount = scanOperatorInput.nextInt();
			System.out.println("Enter Number of seat in 1st Row");
			initialSeatCount = scanOperatorInput.nextInt();
			seats = rowService.designSeatingForClass("gold", rowCount, initialSeatCount);
			seating.put("gold", seats);
			isAdded = screenService.addSeatingsToScreen(seating, screenName);
		} catch (InvalidDesignInputException invalidDesignInputException) {
			logger.error(invalidDesignInputException.getMessage());
		}
		if (isAdded) {
			System.out.println("Seating is set");
		}
	}

	private void addScreenOption() {
		boolean isAdded = false;
		Screen screen = new Screen();
		System.out.println("Enter New Screen Name : ");
		scanOperatorInput.nextLine();
		screen.setScreenName(scanOperatorInput.nextLine());
		try {
			isAdded = screenService.addNewScreen(screen);
		} catch (NullException nullException) {
			logger.error(nullException.getMessage());
		}
		if (isAdded)
			System.out.println("New Screen " + screen.getScreenName() + " Added SuccessFully");
		else
			logger.error("Screen Not Added");
	}
}
