package com.yash.tcvm.configurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.enums.IngredientsWithMaxCapacity;
import com.yash.tcvm.util.ConfigurationUtil;

public class CoffeeConfiguration extends AbstractDrinkConfigurer {

	private static DrinkConfigurer drinkConfigurer;
	
	private static Properties properties;

	private CoffeeConfiguration() {
	}

	static {
		properties=ConfigurationUtil.readPropertyFile();
		drinkConfigurer = new CoffeeConfiguration();
	}
	
	public static DrinkConfigurer getDrinkConfigurer() {
		return drinkConfigurer;
	}

	public void configIngredientConsumption() {
		Map<IngredientsWithMaxCapacity, Double> ingredientsConsumption = new HashMap<>();
		double waterConsumption=Double.parseDouble(properties.getProperty("COFFEE_WATAR_COMSUMPTION"));
		double sugarConsumption=Double.parseDouble(properties.getProperty("COFFEE_SUGAR_COMSUMPTION"));
		double milkConsumption=Double.parseDouble(properties.getProperty("COFFEE_MILK_COMSUMPTION"));
		double coffeeConsumption=Double.parseDouble(properties.getProperty("COFFEE_COFFEE_COMSUMPTION"));
		ingredientsConsumption.put(IngredientsWithMaxCapacity.COFFEE, coffeeConsumption);
		ingredientsConsumption.put(IngredientsWithMaxCapacity.MILK, milkConsumption);
		ingredientsConsumption.put(IngredientsWithMaxCapacity.WATER, waterConsumption);
		ingredientsConsumption.put(IngredientsWithMaxCapacity.SUGAR, sugarConsumption);

		setIngredientConsumption(ingredientsConsumption);
	}

	public void configIngredientWastage() {
		Map<IngredientsWithMaxCapacity, Double> ingredientsWastage = new HashMap<>();
		double watarWastage=Double.parseDouble(properties.getProperty("COFFEE_WATER_WASTAGE"));
		double sugarWastage=Double.parseDouble(properties.getProperty("COFFEE_SUGAR_WASTAGE"));
		double milkWastage=Double.parseDouble(properties.getProperty("COFFEE_MILK_WASTAGE"));
		double coffeeWastage=Double.parseDouble(properties.getProperty("COFFEE_COFFEE_WASTAGE"));
		ingredientsWastage.put(IngredientsWithMaxCapacity.COFFEE, coffeeWastage);
		ingredientsWastage.put(IngredientsWithMaxCapacity.MILK, milkWastage);
		ingredientsWastage.put(IngredientsWithMaxCapacity.WATER, watarWastage);
		ingredientsWastage.put(IngredientsWithMaxCapacity.SUGAR, sugarWastage);

		setIngredientWastage(ingredientsWastage);
	}

	public void configDrinkType() {
		setDrinkType(Drink.COFFEE);
	}

	public void configDrinkRate() {
		double rate=Double.parseDouble(properties.getProperty("COFFEE_RATE"));
		setDrinkRate(rate);
	}

}
