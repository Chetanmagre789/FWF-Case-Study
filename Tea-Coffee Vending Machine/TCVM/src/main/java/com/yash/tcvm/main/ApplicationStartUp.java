package com.yash.tcvm.main;

import org.apache.log4j.Logger;

public class ApplicationStartUp {
	private static Logger logger = Logger.getLogger(ApplicationStartUp.class);
	public static void main(String[] args) {
		logger.info(" Application started ");
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.displayMenu();
	}
}
