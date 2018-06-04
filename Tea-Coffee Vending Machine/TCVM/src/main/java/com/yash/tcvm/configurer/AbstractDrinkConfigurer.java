package com.yash.tcvm.configurer;

import java.util.Map;

import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.enums.IngredientsWithMaxCapacity;

public abstract class AbstractDrinkConfigurer implements DrinkConfigurer {

	private Map<IngredientsWithMaxCapacity, Double> ingredientConsumption;

	private Map<IngredientsWithMaxCapacity, Double> ingredientWastage;

	private double drinkRate;

	private Drink drinkType;

	public AbstractDrinkConfigurer() {
		initDrinkConfig();
	}

	private void initDrinkConfig() {

		configIngredientConsumption();

		configIngredientWastage();

		configDrinkType();

		configDrinkRate();

	}

	public Map<IngredientsWithMaxCapacity, Double> getIngredientConsumption() {
		return ingredientConsumption;
	}

	public void setIngredientConsumption(Map<IngredientsWithMaxCapacity, Double> ingredientConsumption) {
		this.ingredientConsumption = ingredientConsumption;
	}

	public Map<IngredientsWithMaxCapacity, Double> getIngredientWastage() {
		return ingredientWastage;
	}

	public void setIngredientWastage(Map<IngredientsWithMaxCapacity, Double> ingredientWastage) {
		this.ingredientWastage = ingredientWastage;
	}

	public double getDrinkRate() {
		return drinkRate;
	}

	public void setDrinkRate(double drinkRate) {
		this.drinkRate = drinkRate;
	}

	public Drink getDrinkType() {
		return drinkType;
	}

	public void setDrinkType(Drink drinkType) {
		this.drinkType = drinkType;
	}
	
}
