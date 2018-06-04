package com.yash.tcvm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.yash.tcvm.exception.*;

public class FileUtil {

	public FileReader readFileReturnsFileReader(String filepath) throws IOException {
		File file = new File(filepath);
		FileReader fileReader = new FileReader(file);
		return fileReader;

	}

	public void displayFile(String filePath) {
		filePathIsNull(filePath);
		filePathIsEmpty(filePath);
		readFile(filePath);

	}

	public void filePathIsNull(String filePath) {
		if (filePath == null) {
			throw new FileNotPresentException("File cannot be null");
		}
	}

	public void filePathIsEmpty(String filePath) {
		if (filePath == "") {
			throw new FileNameEmptyException("File Name can't be empty.");
		}
	}

	public String readFile(String filePath) {
		try {

			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer contents = new StringBuffer();
			String text = null;
			fileContentIsEmpty(bufferedReader);

			while ((text = bufferedReader.readLine()) != null) {
				contents.append(text).append(System.getProperty("line.separator"));

			}
			bufferedReader.close();

			System.out.println(contents.toString());

		} catch (FileNotFoundException fileNotFoundException) {
			throw new FileNotPresentException("File not found !");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		return "";

	}

	public void fileContentIsEmpty(BufferedReader bufferedReader) throws IOException {
		String text = null;
		if ((text = bufferedReader.readLine()) == null) {
			throw new FileContentEmptyException("File Contents Are Empty");

		}
	}

}
