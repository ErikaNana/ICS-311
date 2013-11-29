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
					continue;
				}
				if (next.matches("(?s).*tie.*")){
					create = false;
					tie = true;
					continue;
				}
				if(create) {
					//create the nodes;
					if (!next.matches("(?s).*ID.*")) {
						//get data attributes
						String[] words = next.split(" ");
						//sanitize array.  \r is a carriage return
						for(int i = 0; i < words.length; i++) {
							words[i] = words[i].replaceAll("\\r|\\n", "");
						}
						//first column is always the id
						if (words.length == 1) {			
							//\r is a carriage return
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
				}
				//System.out.println(next);
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND!");
			return null;
		}
		return graph;
	}
}
