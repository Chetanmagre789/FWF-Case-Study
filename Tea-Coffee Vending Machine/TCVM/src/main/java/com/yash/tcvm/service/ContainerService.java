package com.yash.tcvm.service;

import java.util.List;

import com.yash.tcvm.enums.IngredientsWithMaxCapacity;
import com.yash.tcvm.exception.ContainerOverflowException;
import com.yash.tcvm.model.Container;

public interface ContainerService {

	Container getContainerByIngredient(IngredientsWithMaxCapacity ingredient);

	List<Container> getAllContainers();
	
	boolean refillContainer(Container containerToRefill, Double quantity) throws ContainerOverflowException;

	boolean updateAllContainers(List<Container> containers);
	
	boolean resetAllContainers();
	
}
