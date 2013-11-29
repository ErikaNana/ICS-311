package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class VNAParser {
	//does no error checking on file names
	
	public static DirectedGraph generateGraph(String fileName) {
		boolean create = false;
		boolean tie = false;
		
		DirectedGraph graph = new DirectedGraph();
		File file = new File(fileName);
		System.out.println("file:  " + file);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			//read file in line by line
			Scanner fileReader = new Scanner(br);
			fileReader.useDelimiter("\n");
			while(fileReader.hasNext()) {
				String next = fileReader.next();
				//need the (?s) to match the newline caused by the delimiter
				if (next.matches("(?s).*ode.*")) {
					create = true;
					System.out.println("CREATE IS TRUE!");
					continue;
				}
				if (next.matches("(?s).*tie.*")){
					create = false;
					tie = true;
				}
				if(create) {
					//create the nodes;
					if (!next.matches("(?s).*ID.*")) {
						//get data attributes
						String[] words = next.split(" ");
						System.out.println("creating!");
						//first column is always the id
						if (words.length == 1) {			
							//\r is a carriage return
							graph.insertVertex(words[0].replaceAll("\\r|\\n", ""));
						}
						else {
							graph.insertVertex(words[0], words[1]);
							for(int i = 2; i < words.length; i++) {
								graph.getVertex(words[0]).addData(words[i]);
							}
						}
					}
				}
				if (tie) {
					//create the edges
					if (!next.matches("(?s).*from.*")) {
						System.out.println("tie dye!");
					}
				}
				System.out.println(next);
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND!");
			return null;
		}
		return graph;
	}
}
