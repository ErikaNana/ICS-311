package code;

public class Arc {

	private Vertex startVertex;
	private Vertex endVertex;
	private Object data;
	private Object anOne;
	private Object anTwo;
	
	public Arc(Vertex start, Vertex end) {
		this.startVertex = start;
		this.endVertex = end;
		this.data = null;
	}
	public Arc(Vertex start, Vertex end, Object data) {
		this.startVertex = start;
		this.endVertex = end;
		this.data = data;
	}
	public Vertex getStartVertex() {
		return startVertex;
	}
	
	public Vertex getEndVertex() {
		return endVertex;
	}
	public void setStartVertex(Vertex vertex) {
		this.startVertex = vertex;
	}
	public void setEndVertex(Vertex vertex) {
		this.endVertex = vertex;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getData() {
		return data;
	}
	//overloaded so that when inserted into BTree, it will store by end value
	public String toString() {
		return endVertex.toString();
	}
	public String getFullArc() {
		return startVertex.toString() + " to " + endVertex.toString();
	}
	public void setAnnotation(Object one, Object two) {
		anOne = one;
		anTwo = two;
	}
	public Object getAnnotation(Object annotation) {
		if (annotation == anOne) {
			return anOne;
		}
		else if (annotation == anTwo) {
			return anTwo;
		}
		return null;
	}
	public void removeAnnotation(Object annotation) {
		if ((annotation == anOne) || (annotation == anTwo)) {
			if (annotation == anOne) {
				anOne = null;
			}
			else {
				anTwo = null;
			}
		}
	}
	public void clearAnnotations() {
		anOne = null;
		anTwo = null;
	}
}
