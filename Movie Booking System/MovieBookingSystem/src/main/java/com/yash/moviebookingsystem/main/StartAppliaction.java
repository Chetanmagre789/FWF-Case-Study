package com.yash.moviebookingsystem.main;

import org.apache.log4j.Logger;

public class StartAppliaction {

	private static Logger logger = Logger.getLogger(StartAppliaction.class);

	public static void main(String[] args) {
		logger.info("Application Start up.");
		BookMovieSystem bookMovieSystem = new BookMovieSystem();
		bookMovieSystem.displayOperatorMenu();
	}

}
