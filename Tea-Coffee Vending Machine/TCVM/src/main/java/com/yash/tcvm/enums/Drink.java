package com.yash.tcvm.enums;

public enum Drink {

	COFFEE(15), BLACK_COFFEE(10), TEA(10), BLACK_TEA(5);

	private double price;

	Drink(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}
}
