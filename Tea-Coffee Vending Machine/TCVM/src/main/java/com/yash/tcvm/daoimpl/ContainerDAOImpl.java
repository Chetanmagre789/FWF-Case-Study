package com.yash.tcvm.daoimpl;

import java.util.ArrayList;
import java.util.List;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.enums.IngredientsWithMaxCapacity;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.util.JSONUtil;

public class ContainerDAOImpl implements ContainerDAO {

	public ContainerDAOImpl() {
	}

	public boolean insert(List<Container> containers) {
		containers = initiateContainer(containers);
		return JSONUtil.writeObjectInJSONFile(containers);
	}

	private List<Container> initiateContainer(List<Container> containers) {
		if (containers == null || containers.isEmpty())
			containers = new ArrayList<>();

		for (IngredientsWithMaxCapacity ingredient : IngredientsWithMaxCapacity.values()) {
			containers.add(new Container(ingredient, ingredient.getMaxCapacity(), ingredient.getMaxCapacity()));
		}
		return containers;
	}

	public List<Container> getContainers() {
		List<Container> containers = JSONUtil.readObjectFromJSONFile();
		if (containers == null) {
			insert(containers);
			containers = JSONUtil.readObjectFromJSONFile();
		}
		return containers;
	}

	public boolean updateContainers(List<Container> containers) {
		return JSONUtil.writeObjectInJSONFile(containers);
	}

}
