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
					if (!next.matches(".*ID.*")) {
						String[] words = next.split(" ");
						graph.insertVertex(words[0]);
					}
				}
				if (tie) {
					if (!next.matches("from.*")) {
						String[] info = next.split(" ");
						Vertex start = graph.getVertex(info[0]);
						String endVertex = "";
						//get the correct info for end
						for (int i = 1; i < info.length; i++) {
							if (endVertex.isEmpty()) {
								if (!info[i].isEmpty()) {
									endVertex = info[i];
									break;
								}
							}
						}
						Vertex end = graph.getVertex(endVertex);
						graph.insertArc(start, end);
/*						File fileBAH = new File("out.txt");
						FileOutputStream fos = new FileOutputStream(fileBAH);
						PrintStream ps = new PrintStream(fos);
						System.setOut(ps);
						Arc arc = graph.getArc(start, end);
						System.out.println("arc:  " + arc.getFullArc());
						System.out.println("data:  " + arc.getData());;*/
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
