package code;

import java.util.Comparator;

//only used for DFS, is reversed so can get descending order
public class VertexComparable implements Comparator<Vertex> {

	public int compare(Vertex one, Vertex two) {
		if ((Integer) one.getAnnotation("finish") > (Integer) two.getAnnotation("finish")) {
			return -1;
		}
		if ((Integer) one.getAnnotation("finish") < (Integer) two.getAnnotation("finish")) {
			return 1;
		}
		return 0;
	}

}
