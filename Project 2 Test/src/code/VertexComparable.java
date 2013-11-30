package code;

import java.util.Comparator;

//only used for DFS, is reversed so can get descending order
public class VertexComparable implements Comparator<Vertex> {

	@Override
	public int compare(Vertex one, Vertex two) {
		if ((int) one.getAnnotation("finish") > (int) two.getAnnotation("finish")) {
			return -1;
		}
		if ((int) one.getAnnotation("finish") < (int) two.getAnnotation("finish")) {
			return 1;
		}
		return 0;
	}

}
