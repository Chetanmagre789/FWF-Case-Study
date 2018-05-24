package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.NullException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.service.ScreenService;

public class ScreenServiceImplTest {

	private ScreenDAO screenDAO = mock(ScreenDAO.class);

	private ScreenService screenService = new ScreenServiceImpl(screenDAO);

	@Test(expected = NullException.class)
	public void addNewScreen_WhenNullIsGiven_ThrowNullException() {
		screenService.addNewScreen(null);
	}

	@Test(expected = NullException.class)
	public void addNewScreen_WhenEmptyScreenIsGiven_ThrowNullException() {
		Screen screen = new Screen("");
		screenService.addNewScreen(screen);
	}

	@Test
	public void addNewScreen_WhenAnExistingScreenIsGiven_ShouldReturnFalse() {
		Screen screen = new Screen("Screen 1");
		when(screenDAO.getScreens()).thenReturn(Arrays.asList(new Screen("screen 1")));
		assertFalse(screenService.addNewScreen(screen));
	}

	@Test
	public void addNewScreen_WhenNewScreenIsGiven_ShouldReturnTrue() {
		Screen screen = new Screen("Screen 2");
		when(screenDAO.getScreens()).thenReturn(Arrays.asList(new Screen("screen 1")));
		when(screenDAO.addScreen(screen)).thenReturn(true);
		assertTrue(screenService.addNewScreen(screen));
	}

	@Test
	public void addNewScreen_WhenFourthNewScreenIsGiven_ShouldReturnFalse() {
		Screen screen = new Screen("Screen 4");
		List<Screen> screens = Arrays.asList(new Screen("screen 1"), new Screen("screen 2"), new Screen("screen 3"));
		when(screenDAO.getScreens()).thenReturn(screens);
		when(screenDAO.addScreen(screen)).thenReturn(false);
		assertFalse(screenService.addNewScreen(screen));
	}
	
	@Test(expected= NullException.class)
	public void addMovieToScreen_WhenNullIsGiven_ThrowNullException() {
		screenService.addMovieToScreen("screen 1",null);
	}
	
	@Test
	public void addMovieToScreen_WhenScreenDoNotHaveSeatingArragement_ShouldReturnFalse() {
		Movie movie = new Movie(101, "deadpool", "02:30", "Marvel", Arrays.asList("Wade Willsion"));
		List<Screen> screens = Arrays.asList(new Screen("screen 1"), new Screen("screen 2"), new Screen("screen 3"));
		when(screenDAO.getScreens()).thenReturn(screens);
		assertFalse(screenService.addMovieToScreen("screen 1",movie));
	}
	
	@Test
	public void addMovieToScreen_WhenScreenNotAvailable_ShouldReturnFalse() {
		Movie movie = new Movie(101, "deadpool", "02:30", "Marvel", Arrays.asList("Wade Willsion"));
		Screen screen = new Screen();
		screen.setScreenName("screen 1");
		screen.setSeatingArrangement(new LinkedHashMap<String, List<Row>>());
		List<Screen> screens = Arrays.asList(screen);
		when(screenDAO.getScreens()).thenReturn(screens);
		assertFalse(screenService.addMovieToScreen("screen 3",movie));
	}
	
	@Test
	public void addMovieToScreen_WhenObjectIsGiven_ShouldAddMoveToScreenAndReturnTrue() {
		Movie movie = new Movie(101, "deadpool", "02:30", "Marvel", Arrays.asList("Wade Willsion"));
		Screen screen = new Screen();
		screen.setScreenName("screen 1");
		screen.setSeatingArrangement(new LinkedHashMap<String, List<Row>>());
		List<Screen> screens = Arrays.asList(screen);
		when(screenDAO.getScreens()).thenReturn(screens);
		assertTrue(screenService.addMovieToScreen("screen 1",movie));
	}
	
	
}
