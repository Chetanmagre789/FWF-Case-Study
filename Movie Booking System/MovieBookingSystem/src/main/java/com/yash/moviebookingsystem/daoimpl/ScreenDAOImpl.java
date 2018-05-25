package com.yash.moviebookingsystem.daoimpl;

import java.util.List;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.util.JSONUtil;

public class ScreenDAOImpl implements ScreenDAO {

	private JSONUtil jsonUtil = JSONUtil.getInstance();

	private List<Screen> screens = null;

	public ScreenDAOImpl(JSONUtil jsonUtil) {
		this.jsonUtil = jsonUtil;
	}

	public ScreenDAOImpl() {
	}

	public List<Screen> getScreens() {
		return jsonUtil.readObjectFromJSONFile();
	}

	public boolean addScreen(Screen screen) {
		boolean addScreenStatus = false;
		screens = jsonUtil.readObjectFromJSONFile();
		screens.add(screen);
		int addStatus = jsonUtil.writeObjectInJSONFile(screens);
		if (addStatus == 1) {
			addScreenStatus = true;
		}
		return addScreenStatus;
	}

	public boolean updateScreens(List<Screen> screens) {
		boolean isUpdated = false;
		if (jsonUtil.writeObjectInJSONFile(screens) == 1) {
			isUpdated = true;
		}
		return isUpdated;
	}

}
