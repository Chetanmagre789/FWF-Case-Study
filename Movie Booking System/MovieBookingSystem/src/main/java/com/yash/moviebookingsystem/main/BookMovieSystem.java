package com.yash.moviebookingsystem.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

	private ScreenService screenService = new ScreenServiceImpl();

	private RowService rowService = new RowServiceImpl();

	private ShowService showService = new ShowServiceImpl();

	private Scanner scanOperatorInput = new Scanner(System.in);

	public void displayOperatorMenu() {
		int operatorInput = 0;
		do {
			System.out.println("---------------- Movie Booking System ---------------");
			System.out.println(
					"1. Add Screen \n2. Add Seating Arrangement To Screen \n3. Add Movie To Screen \n4. Add Shows For Movie \n5. Check Available Shows \n0. Exit \n");
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

	private void checkAvailableShowsOption() {
		System.out.println("Enter Movie name :");
		scanOperatorInput.nextLine();
		String movieName = scanOperatorInput.nextLine();
		List<Show> shows= showService.getShowsByMovieName(movieName);
		for (Show show : shows) {
			System.out.print(show.getShowTime()+"   ");
		}
		System.out.println();
		System.out.println("Enter Show time To Check Seats :");
		String timeOfShow=scanOperatorInput.nextLine();
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
			System.out.println(exception.getMessage());
		}

	}

	private void addMovieToScreenOption() {
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
			screenService.addMovieToScreen(screenName, movie);
		} catch (NullException nullException) {
			System.out.println(nullException.getMessage());
		}
	}

	private void addSeatingArrangementToScreenOption() {
		Map<String, List<Row>> seating = new LinkedHashMap<>();
		String screenName = null;
		int rowCount;
		int initialSeatCount;
		boolean isAdded = false;
		do {
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
				System.out.println(invalidDesignInputException.getMessage());
			}
		} while (!isAdded);
		System.out.println(screenName);
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
			System.out.println(nullException.getMessage());
		}
		if (isAdded)
			System.out.println("New Screen " + screen.getScreenName() + " Added SuccessFully");
		else
			System.out.println("Screen Not Added");
	}
}
