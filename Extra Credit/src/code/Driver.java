package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static int SORTED = 1;
	public static int UNSORTED = 2;
	
	public static void main(String[] args) {
		//run the 100 file
		ArrayList<String> input = new ArrayList<String>();
		
		input = loadFile("unsorted-100.txt");
		runSorts(input, 1, 100, UNSORTED);
		input = null;
		
		input = loadFile("sorted-100.txt");
		runSorts(input,1,100,SORTED);
		input = null;
		
		//run the 1000 file
		input = loadFile("unsorted-1000.txt");
		runSorts(input, 1, 1000, UNSORTED);
		input = null;
		
		input = loadFile("sorted-1000.txt");
		runSorts(input,1,1000,SORTED);
		input = null;
		
		//run the 10,000 file
		input = loadFile("unsorted-10000.txt");
		runSorts(input, 1, 10000, UNSORTED);
		input = null;
		
		input = loadFile("sorted-10000.txt");
		runSorts(input,1,10000,SORTED);
		input = null;
		
		//run the 100,000 file
		input = loadFile("unsorted-100000.txt");
		runSorts(input, 1, 100000, UNSORTED);
		input = null;
		
		input = loadFile("sorted-100000.txt");
		runSorts(input,1,100000,SORTED);
		input = null;
		
		//run the 1,000,000 file
		input = loadFile("unsorted-1000000.txt");
		runSorts(input, 1, 1000000, UNSORTED);
		input = null;
		
		input = loadFile("sorted-1000000.txt");
		runSorts(input,1,1000000,SORTED);
		input = null;
	}
	
	public static ArrayList<String> loadFile(String fileName){
		ArrayList<String> input = new ArrayList<String>();
		File file = new File(fileName);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			//read in file line by line
			Scanner fileReader = new Scanner(br);
			fileReader.useDelimiter("\n");
			while (fileReader.hasNext()) {
				input.add(fileReader.next().trim()); //clean trailing white space
			}
			fileReader.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}		
		return input;
	}
	
	public static void printHeader(int sort,int size) {
		System.out.println("");
		Utils.printDivider();
		if (sort == SORTED) {
			System.out.print("Sorted Test:  ");
		}
		else {
			System.out.print("Unsorted Test:  ");
		}
		System.out.printf("%-10s", size + " keys");
	}
	
	public static void runSorts(ArrayList<String> input, int p, int r, int type) {
		printHeader(type,r);
		Insertion insertion = new Insertion(input);
		Utils.sortAndPrint(insertion, 0, 0,"Insertion");
		insertion = null;
		
		Heap heap = new Heap(input);
		Utils.sortAndPrint(heap, 0, 0,"Heap");
		heap = null;
		
		Merge merge = new Merge(input);
		Utils.sortAndPrint(merge, p, r, "Merge");
		merge = null;
		
		Quick quick = new Quick(input);
		Utils.sortAndPrint(quick, p, r, "Quick");
		quick = null;
		
		System.out.println("");
		Utils.printDivider();
		System.out.println("");
	}
}
