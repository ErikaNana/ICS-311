package code;

public class Arc {

	private Vertex startVertex;
	private Vertex endVertex;
	private Object data;
	
	public Arc(Vertex start, Vertex end) {
		this.startVertex = start;
		this.endVertex = end;
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
}
