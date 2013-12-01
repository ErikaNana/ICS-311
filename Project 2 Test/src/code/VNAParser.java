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
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			//read file in line by line
			Scanner fileReader = new Scanner(br);
			fileReader.useDelimiter(Pattern.compile("[\\r\\n]+"));
			while(fileReader.hasNext()) {
				String next = fileReader.next();
				if (next.contains("*node") || next.contains("*Node")) {
					create = true;
					continue;
				}
				if (next.contains("*tie") || next.contains("*Tie")){
					create = false;
					tie = true;
					continue;
				}
				if (create) {
					if (!next.matches("^ID.*")) { //match beginning of the line
						String[] words = next.split("\\s+");
						graph.insertVertex(words[0]);
					}
				}
				if (tie) {
					if (!next.matches("from.*")) {
						//split by ALL the white space
						String[] info = next.split("\\s+");
						Vertex start = graph.getVertex(info[0]);
						Vertex end = graph.getVertex(info[1]);
						graph.insertArc(start, end);
					}
				}
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			return null;
		}
		return graph;
	}
}
