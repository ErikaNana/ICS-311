package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class VNAParser {
	//does no error checking on file names
	
	public static DirectedGraph generateGraph(String fileName) {
		boolean create = false;
		boolean tie = false;
		
		DirectedGraph graph = new DirectedGraph();
		File file = new File(fileName);
		//System.out.println("file:  " + file);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			//read file in line by line
			Scanner fileReader = new Scanner(br);
			fileReader.useDelimiter(Pattern.compile("[\\r\\n;]+"));
			while(fileReader.hasNext()) {
				String next = fileReader.next();
				//need the (?s) to match the newline caused by the delimiter
				if (next.matches("(?s).*ode.*")) {
					System.out.println("create!");
					create = true;
					continue;
				}
				if (next.matches("(?s).*ie.*")){
					System.out.println("tie!");
					create = false;
					tie = true;
					continue;
				}
				System.out.println("next:  " + next);
				if (create) {
					if (!next.matches(".*ID.*")) {
						//System.out.println("I MATCH");
						String[] words = next.split(" ");
						//System.out.println("length:  " + words[0].length());
						graph.insertVertex(words[0]);
						//Vertex vertex = graph.getVertex(words[0]);
						//System.out.println("vertex:  " + vertex);
						//first column is always the id
						if (words.length > 1) {
							for(int i = 1; i < words.length; i++) {
								graph.getVertex(words[0]).addData(words[i]);
							}
						}
					}
				}
				if (tie) {
					if (!next.matches("from.*")) {
						System.out.println("tie dye!");
						String[] info = next.split(" ");
						for (int i = 0; i < info.length; i++) {
							System.out.println(info[i]);
							System.out.println("length:  " + info[i].length());
						}
						//System.out.println("info:  " + info[0].getClass());
						Vertex start = graph.getVertex(info[0].toString());
						Vertex end = graph.getVertex(info[1]);
						//System.out.println("vertex start:  " + start);
						//System.out.println("vertex end:  " + end);
						if (info.length == 2) {
							graph.insertArc(start, end);
						}
						else {//add weight
							graph.insertArc(start, end,info[2]);
						}
					}
				}
/*				if(create) {
					//create the nodes;
					if (!next.matches("(?s).*ID.*")) {
						//get data attributes
						String[] words = next.split(" ");
						//sanitize array.  \r is a carriage return
						for(int i = 0; i < words.length; i++) {
							words[i] = words[i].replaceAll("\\r|\\n", "");
						}
						graph.insertVertex(words[0]);
						//first column is always the id
						if (words.length == 1) {			
							graph.insertVertex(words[0]);
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
						String[] info = next.split(" ");
						for(int i = 0; i < info.length; i++) {
							info[i] = info[i].replaceAll("\\r|\\n", "");
						}
						Vertex start = graph.getVertex(info[0]);
						Vertex end = graph.getVertex(info[1]);
						if (info.length == 2) {
							graph.insertArc(start, end);
						}
						else {//add weight
							graph.insertArc(start, end,info[2]);
						}
					}
				}*/
				//System.out.println(next);
				System.out.println("");
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			return null;
		}
		return graph;
	}
}
