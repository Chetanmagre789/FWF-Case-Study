package com.yash.tcvm.model;

import com.yash.tcvm.enums.IngredientsWithMaxCapacity;

public class Container {

	private IngredientsWithMaxCapacity ingredient;
	private double maxCapacity;
	private double availableCapacity;

	public Container() {
	}
	
	public Container(IngredientsWithMaxCapacity ingredient, double maxCapacity, double availableCapacity) {
		this.ingredient = ingredient;
		this.maxCapacity = maxCapacity;
		this.availableCapacity = availableCapacity;
	}

	public IngredientsWithMaxCapacity getIngredient() {
		return ingredient;
	}

	public void setIngredient(IngredientsWithMaxCapacity ingredient) {
		this.ingredient = ingredient;
	}

	public double getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(double maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public double getAvailableCapacity() {
		return availableCapacity;
	}

	public void setAvailableCapacity(double availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

	@Override
	public String toString() {
		return "Container [ingredient=" + ingredient + ", maxCapacity=" + maxCapacity + ", availableCapacity="
				+ availableCapacity + "]";
	}

}
