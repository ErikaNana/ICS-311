package code;

import java.util.HashMap;

public class Arc {

	private Vertex startVertex;
	private Vertex endVertex;
	private Object data;
	private HashMap<Object,Object> annotations;
	
	public Arc(Vertex start, Vertex end) {
		this.startVertex = start;
		this.endVertex = end;
		this.data = null;
		this.annotations = new HashMap<Object,Object>();
	}
	public Arc(Vertex start, Vertex end, Object data) {
		this.startVertex = start;
		this.endVertex = end;
		this.data = data;
		this.annotations = new HashMap<Object,Object>();
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
	public void setAnnotation(Object key, Object value) {
		annotations.put(key, value);
	}
	public Object getAnnotation(Object annotation) {
		return annotations.get(annotation);
	}
	public void removeAnnotation(Object annotation) {
		annotations.remove(annotation);
	}
	public void clearAnnotations() {
		annotations.clear();
	}
}
