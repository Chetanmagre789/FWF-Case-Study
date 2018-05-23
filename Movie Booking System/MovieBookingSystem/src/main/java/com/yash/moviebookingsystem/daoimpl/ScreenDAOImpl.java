package com.yash.moviebookingsystem.daoimpl;

import java.util.ArrayList;
import java.util.List;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.model.Screen;

public class ScreenDAOImpl implements ScreenDAO {
	
	private static ScreenDAO screenDAO;
	
	private List<Screen> screens;
	
	public static ScreenDAO getInstanceOfScreenDAO(){
		if(screenDAO==null){
			screenDAO = new ScreenDAOImpl();
		}
		return screenDAO;
	}
	
	public ScreenDAOImpl() {
		screens=new ArrayList<Screen>();
	}
	
	public List<Screen> getScreens() {
		return screens;
	}

	public boolean addScreen(Screen screen) {
		return screens.add(screen);
	}

}
