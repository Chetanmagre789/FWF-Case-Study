package com.yash.tcvm.builder;

import com.yash.tcvm.configurer.BlackTeaConfiguration;
import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.model.Order;

public class BlackTeaBuilder extends AbstractDrinkBuilder {

	public BlackTeaBuilder() {
		setDrinkConfigurer(BlackTeaConfiguration.getDrinkConfigurer());
	}

	@Override
	public void prepareDrink(Order order) throws ContainerUnderflowException {
		if (order.getDrink() == Drink.BLACK_TEA) {
				super.prepareDrink(order);
			
		} else {
			throw new IllegalArgumentException(
					"Wrong Drink Type, required " + Drink.BLACK_TEA + " and found " + order.getDrink());
		}
	}

	public static DrinkBuilder getDrinkBuilder() {
		return new BlackTeaBuilder();
	}
}