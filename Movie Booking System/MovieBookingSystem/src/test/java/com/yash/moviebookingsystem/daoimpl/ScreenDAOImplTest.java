package com.yash.moviebookingsystem.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.util.JSONUtil;

public class ScreenDAOImplTest {
	
	private JSONUtil jsonUtil= mock(JSONUtil.class);
	private ScreenDAO screenDAO=new ScreenDAOImpl(jsonUtil);
	
	@Test
	public void getScreens_ShouldReturnListOfScreens() {
		when(jsonUtil.readObjectFromJSONFile()).thenReturn(Arrays.asList(new Screen()));
		assertEquals(1, screenDAO.getScreens().size());
	}
	
	@Test
	public void addScreen_WhenGivenScreenIsNotAdded_ShouldReturnFalse() {
		Screen screen = new Screen("screen 3");
		List<Screen> screens = new ArrayList<>();
		when(jsonUtil.readObjectFromJSONFile()).thenReturn(screens);
		screens.add(screen);
		when(jsonUtil.writeObjectInJSONFile(screens)).thenReturn(0);
		assertFalse(screenDAO.addScreen(screen));
	}
	
	@Test
	public void addScreen_WhenGivenScreenIsAdded_ShouldReturnTrue() {
		Screen screen = new Screen("screen 3");
		List<Screen> screens = new ArrayList<>();
		when(jsonUtil.readObjectFromJSONFile()).thenReturn(screens);
		screens.add(screen);
		when(jsonUtil.writeObjectInJSONFile(screens)).thenReturn(1);
		assertTrue(screenDAO.addScreen(screen));
	}
	
	@Test
	public void updateScreens_WhenListOfScreensGivenIsNotUpdated_ShouldReturnFalse(){
		List<Screen> screens = new ArrayList<>();
		when(jsonUtil.writeObjectInJSONFile(screens)).thenReturn(0);
		assertFalse(screenDAO.updateScreens(screens));
	}
	
	@Test
	public void updateScreens_WhenListOfScreensGivenIsUpdated_ShouldReturnTrue(){
		List<Screen> screens = new ArrayList<>();
		when(jsonUtil.writeObjectInJSONFile(screens)).thenReturn(1);
		assertTrue(screenDAO.updateScreens(screens));
	}
}
