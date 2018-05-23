package com.yash.moviebookingsystem.serviceimpl;

import java.util.List;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exception.NullException;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.service.ScreenService;

public class ScreenServiceImpl implements ScreenService {

	private ScreenDAO screenDAO;

	public ScreenServiceImpl(ScreenDAO screenDAO) {
		this.screenDAO = screenDAO;
	}

	public boolean addNewScreen(Screen screen) {
		boolean isAdded = false;
		checkForNullOrEmptyScreenName(screen);
		List<Screen> screens = screenDAO.getScreens();
		if (!screens.isEmpty())
			for (Screen screenInRepository : screens) {
				if (isScreenExist(screen, screenInRepository)) {
					isAdded = false;
					break;
				}
				isAdded = true;
			}
		if (isAdded)
			isAdded = screenDAO.addScreen(screen);
		return isAdded;
	}

	private boolean isScreenExist(Screen screen, Screen screenInRepository) {
		return screenInRepository.getScreenName().equalsIgnoreCase(screen.getScreenName());
	}

	private void checkForNullOrEmptyScreenName(Screen screen) {
		if (screen == null || screen.getScreenName().trim().equals("")) {
			throw new NullException("Can't add null screen or Empty screenName");
		}
	}

}
