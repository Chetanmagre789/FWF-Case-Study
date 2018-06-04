package com.yash.tcvm.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationUtil {

	public static Properties readPropertyFile() {

		FileReader configurationReader;
		Properties properties = new Properties();
		try {
			configurationReader = new FileReader("src/main/resources/configuration.properties");
			properties.load(configurationReader);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties;

	}
}
