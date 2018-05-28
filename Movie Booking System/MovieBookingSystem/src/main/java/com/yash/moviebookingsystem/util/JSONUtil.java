package com.yash.moviebookingsystem.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.yash.moviebookingsystem.model.Screen;

public class JSONUtil {

	public int writeObjectInJSONFile(List<Screen> screens) {
		Gson gson = new GsonBuilder().create();
		int successStatus=0;
		try {
			String jsonInString = gson.toJson(screens);
			FileWriter fileWriter = new FileWriter("src/main/resources/Json/Screen.json");
			fileWriter.write(jsonInString);
			fileWriter.close();
			successStatus=1;
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
		return successStatus;
	}

	public List<Screen> readObjectFromJSONFile() {
		Gson gson = new GsonBuilder().create();
		List<Screen> screenlist = null;
		FileReader fileReader;
		try {
			fileReader = new FileReader("src/main/resources/Json/Screen.json");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String jsonfromString = bufferedReader.readLine();
			screenlist = gson.fromJson(jsonfromString, new TypeToken<List<Screen>>() {
			}.getType());
			bufferedReader.close();
			if(screenlist==null){
				screenlist= new ArrayList<>();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenlist;
	}
}
