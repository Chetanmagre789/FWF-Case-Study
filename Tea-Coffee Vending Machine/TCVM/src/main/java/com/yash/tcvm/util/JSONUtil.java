package com.yash.tcvm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.model.Container;

public class JSONUtil {

	public static boolean writeObjectInJSONFile(List<Container> containers) {
		Gson gson = new GsonBuilder().create();
		boolean successStatus = false;
		try {
			String jsonInString = gson.toJson(containers);
			FileWriter fileWriter = new FileWriter("src/main/resources/container.json");
			fileWriter.write(jsonInString);
			fileWriter.close();
			successStatus = true;
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
		return successStatus;
	}

	public static List<Container>  readObjectFromJSONFile() {
		Gson gson = new GsonBuilder().create();
		List<Container> containers = null;
		FileReader fileReader;
		try {
			fileReader = new FileReader("src/main/resources/container.json");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String jsonfromString = bufferedReader.readLine();
			containers = gson.fromJson(jsonfromString, new TypeToken<List<Container>>() {
			}.getType());
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return containers;
	}
	
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static boolean writeJSONToFile(Object object, String filePath, String fileName) throws EmptyException {
		boolean isWritten=false;
		if(filePath.isEmpty()){
			throw new EmptyException("File path is null");
		}
		
		if(fileName.isEmpty()){
			throw new EmptyException("File name is null");
		}
		
		try (FileWriter writer = new FileWriter(filePath.concat(fileName))) {
			gson.toJson(object, writer);
			isWritten=true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isWritten;
	}

	public static String convertObjectToJSONString(Object object) {
		String jsonString = gson.toJson(object);
		System.out.println(jsonString);
		return jsonString;
	}

	public static List<?> readJSONFromFile(String filePath, String fileName) throws FileNotFoundException, EmptyException {
		if(filePath.isEmpty()){
			throw new EmptyException("File path is null");
		}
		
		if(fileName.isEmpty()){
			throw new EmptyException("File name is null");
		}
		
		File fileToBeRead = new File(filePath.concat(fileName));
		
		if(!fileToBeRead.exists()){
			throw new FileNotFoundException("File doesnt exist");
		}
		
		if(fileToBeRead.length() <= 0){
			throw new EmptyException("File is empty");
		}
		
		List<?> list = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			list = objectMapper.readValue(fileToBeRead, List.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static <T> T mapObjectToSpecificModelObject(Class<T> objectType, Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.convertValue(object, objectType);
	}
}