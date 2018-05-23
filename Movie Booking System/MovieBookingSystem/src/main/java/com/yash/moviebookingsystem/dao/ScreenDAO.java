package com.yash.moviebookingsystem.dao;

import java.util.List;

import com.yash.moviebookingsystem.model.Screen;

public interface ScreenDAO {

	List<Screen> getScreens();

	boolean addScreen(Screen screen);

}
