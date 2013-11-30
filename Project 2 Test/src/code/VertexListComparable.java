package code;

import java.util.ArrayList;
import java.util.Comparator;

public class VertexListComparable implements Comparator <ArrayList<Vertex>>{

	@Override
	public int compare(ArrayList<Vertex> listOne, ArrayList<Vertex> listTwo) {
		if (listOne.size() > listTwo.size()) {
			return 1;
		}
		if (listOne.size() < listTwo.size()) {
			return -1;
		}
		return 0;
	}

}
