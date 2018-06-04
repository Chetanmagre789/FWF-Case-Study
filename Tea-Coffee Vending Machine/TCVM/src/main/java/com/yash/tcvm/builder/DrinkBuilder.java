package com.yash.tcvm.builder;

import com.yash.tcvm.configurer.DrinkConfigurer;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.model.Order;

public interface DrinkBuilder {

	void setDrinkConfigurer(DrinkConfigurer drinkConfigurer);

	void prepareDrink(Order order) throws ContainerUnderflowException;

}