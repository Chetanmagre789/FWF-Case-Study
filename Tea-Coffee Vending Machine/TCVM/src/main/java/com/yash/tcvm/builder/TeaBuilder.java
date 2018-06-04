package com.yash.tcvm.builder;

import com.yash.tcvm.configurer.TeaConfiguration;
import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.model.Order;

public class TeaBuilder extends AbstractDrinkBuilder {

	public TeaBuilder() {
		setDrinkConfigurer(TeaConfiguration.getDrinkConfigurer());
	}

	@Override
	public void prepareDrink(Order order) throws ContainerUnderflowException {
		if (order.getDrink() == Drink.TEA) {
				super.prepareDrink(order);
		
		} else {
			throw new IllegalArgumentException(
					"Wrong Drink Type, required " + Drink.TEA + " and found " + order.getDrink());
		}
	}

	public static DrinkBuilder getDrinkBuilder() {
		return new TeaBuilder();
	}
}