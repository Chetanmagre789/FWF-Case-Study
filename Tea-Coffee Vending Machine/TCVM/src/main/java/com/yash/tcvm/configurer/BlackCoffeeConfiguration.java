package com.yash.tcvm.configurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.enums.IngredientsWithMaxCapacity;
import com.yash.tcvm.util.ConfigurationUtil;

public class BlackCoffeeConfiguration extends AbstractDrinkConfigurer {

	private static DrinkConfigurer drinkConfigurer;
	
	private static Properties properties;

	private BlackCoffeeConfiguration() {
	}

	static {
		properties=ConfigurationUtil.readPropertyFile();
		drinkConfigurer = new BlackCoffeeConfiguration();
	}
	
	public static DrinkConfigurer getDrinkConfigurer() {
		return drinkConfigurer;
	}

	public void configIngredientConsumption() {
		Map<IngredientsWithMaxCapacity, Double> ingredientsConsumption = new HashMap<>();
		double waterConsumption=Double.parseDouble(properties.getProperty("BCOFFEE_WATAR_COMSUMPTION"));
		double sugarConsumption=Double.parseDouble(properties.getProperty("BCOFFEE_SUGAR_COMSUMPTION"));
		double coffeeConsumption=Double.parseDouble(properties.getProperty("BCOFFEE_COFFEE_COMSUMPTION"));
		ingredientsConsumption.put(IngredientsWithMaxCapacity.COFFEE, coffeeConsumption);
		ingredientsConsumption.put(IngredientsWithMaxCapacity.WATER, waterConsumption);
		ingredientsConsumption.put(IngredientsWithMaxCapacity.SUGAR, sugarConsumption);

		setIngredientConsumption(ingredientsConsumption);
	}

	public void configIngredientWastage() {
		Map<IngredientsWithMaxCapacity, Double> ingredientsWastage = new HashMap<>();
		double watarWastage=Double.parseDouble(properties.getProperty("BCOFFEE_WATER_WASTAGE"));
		double sugarWastage=Double.parseDouble(properties.getProperty("BCOFFEE_SUGAR_WASTAGE"));
		double coffeeWastage=Double.parseDouble(properties.getProperty("BCOFFEE_COFFEE_WASTAGE"));
		ingredientsWastage.put(IngredientsWithMaxCapacity.COFFEE, coffeeWastage);
		ingredientsWastage.put(IngredientsWithMaxCapacity.WATER, watarWastage);
		ingredientsWastage.put(IngredientsWithMaxCapacity.SUGAR, sugarWastage);

		setIngredientWastage(ingredientsWastage);
	}

	public void configDrinkType() {
		setDrinkType(Drink.BLACK_COFFEE);
	}

	public void configDrinkRate() {
		double rate=Double.parseDouble(properties.getProperty("BCOFFEE_RATE"));
		setDrinkRate(rate);
	}

}
