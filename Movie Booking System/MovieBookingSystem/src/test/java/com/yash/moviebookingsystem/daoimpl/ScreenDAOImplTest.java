package com.yash.moviebookingsystem.daoimpl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.model.Screen;

public class ScreenDAOImplTest {
	
	private ScreenDAO screenDAO;
	
	@Before
	public void setUp(){
		screenDAO = ScreenDAOImpl.getInstanceOfScreenDAO();
	}
	
	@Test
	public void getScreens_ShouldReturnListOfScreens() {
		assertEquals(0, screenDAO.getScreens().size());
	}
	
	@Test
	public void addScreen_WhenGivenScreenIsAdded_ShouldReturnTrue() {
		Screen screen = new Screen("Screen 1");
		assertTrue(screenDAO.addScreen(screen));
	}

}
