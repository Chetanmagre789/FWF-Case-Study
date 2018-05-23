package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.NullException;
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
}
