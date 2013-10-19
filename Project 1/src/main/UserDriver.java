package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.Scanner;

public class UserDriver {

	/**
	 * Driver to run the program
	 */
	public static void main(String[] args) {
		System.out.println("Please type in file name to read:");
		String fileName;
		
		Scanner inputReader = new Scanner(System.in);
		fileName = inputReader.nextLine();
		inputReader.close();
		//check if file exists
		
		File file = new File(fileName);
		int index = 0;
		try {
			//create an array based on the file name
			String [] array = null;
			if (fileName.matches(".*100\\.txt")) {
				array = new String[100];
				System.out.println("100");
			}
			else if(fileName.matches(".*1000\\.txt")) {
				array = new String[1000];
				System.out.println("1000");
			}
			else if(fileName.matches(".*10000\\.txt")) {
				array = new String[10000];
				System.out.println("10000");
			}
			else if(fileName.matches(".*100000\\.txt")) {
				array = new String[100000];
				System.out.println("100000");
			}
			else if(fileName.matches(".*1000000\\.txt")) {
				array = new String[1000000];
				System.out.println("1000000");
			}
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			System.out.println("The file exists!");
			//read file in line by line
			inputReader = new Scanner(br);
			inputReader.useDelimiter("(\n)");
			
			while (inputReader.hasNext()) {
				array[index] = inputReader.next();
				index++;
			}
			
			System.out.println("for loop");
			System.out.println("array length:  " + array.length);
			for (int i = 0; i < array.length; i++) {
				System.out.println(array[i]);
				System.out.println("i:  " + i);
			}
			System.out.println("out of for loop");
		} catch (FileNotFoundException e) {
			System.out.println("File not found.  Please try again.");
		}
		
		inputReader.close();
	}

}
