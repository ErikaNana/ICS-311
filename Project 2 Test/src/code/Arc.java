package code;

public class Arc {

	private Vertex startVertex = null;
	private Vertex endVertex = null;
	
	public Arc(String start, String end) {
		this.startVertex = new Vertex(start);
		this.endVertex = new Vertex(end);
	}
	
	public Vertex getStartVertex() {
		return startVertex;
	}
	
	public Vertex getEndVertex() {
		return endVertex;
	}
}
