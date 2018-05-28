package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.yash.moviebookingsystem.exception.InvalidInputException;
import com.yash.moviebookingsystem.exception.NullException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Show;
import com.yash.moviebookingsystem.service.ScreenService;
import com.yash.moviebookingsystem.service.ShowService;

public class ShowServiceImplTest {

	private ScreenService screenService = mock(ScreenService.class);
	private ShowService showService = new ShowServiceImpl(screenService);

	@Test(expected = NullException.class)
	public void createShows_WhenNoScreenAddedGiven_ThrowNullException() {
		when(screenService.getAllScreens()).thenReturn(new ArrayList<Screen>());
		showService.createShows("deadpool", 3, 500, 300, 150);
	}

	@Test(expected = InvalidInputException.class)
	public void createShows_WhenInvalidMovieNameIsGiven_ThrowInvalidInputException() {
		Movie movie = new Movie(101, "deadpool1", "02:30", "Marvel", Arrays.asList("Wade Willsion"));
		when(screenService.getAllScreens()).thenReturn(Arrays.asList(new Screen("screen 1", movie, null, null)));
		showService.createShows("deadpool", 3, 500, 300, 150);
	}

	@Test
	public void createShows_WhenMovieNameAndNumberOfShowsWithPricesGiven_ShouldReturnListOfShows() {
		Movie movie = new Movie(101, "deadpool", "02:30", "Marvel", Arrays.asList("Wade Willsion"));
		when(screenService.getAllScreens()).thenReturn(Arrays.asList(new Screen("screen 1", movie, null, null)));
		List<Show> shows = showService.createShows("deadpool", 3, 500, 300, 150);
		assertEquals(3, shows.size());
	}

	@Test(expected = InvalidInputException.class)
	public void getShowsByMovieName_WhenInvalidMovieIsGiven_ThrowInvalidInputExecption() {
		showService.getShowsByMovieName("deadpool");
	}
	
	@Test
	public void getShowsByMovieName_WhenValidMovieIsGiven_ShouldReturnListOfShows() {
		Movie movie = new Movie(101, "deadpool", "02:30", "Marvel", Arrays.asList("Wade Willsion"));
		when(screenService.getAllScreens()).thenReturn(Arrays.asList(new Screen("screen 1", movie, null, new ArrayList<Show>())));
		assertEquals(0,showService.getShowsByMovieName("deadpool").size());
	}
	
}
