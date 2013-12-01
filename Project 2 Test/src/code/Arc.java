package code;

import java.util.HashMap;

public class Arc {

	private Vertex startVertex;
	private Vertex endVertex;
	private Data dataList;
	private HashMap<Object,Object> annotations;
	
	public Arc(Vertex start, Vertex end) {
		this.startVertex = start;
		this.endVertex = end;
		this.dataList = new Data();
		this.annotations = new HashMap<Object,Object>();
	}
	public Arc(Vertex start, Vertex end, Object data) {
		this.startVertex = start;
		this.endVertex = end;
		if (dataList == null) {
			this.dataList = new Data();
		}
		this.dataList.addData((String)data);
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
		if (dataList == null) {
			dataList = new Data();
		}
		this.dataList.addData((String) data);
	}
	public Object getData() {
		return dataList.getData();
	}
	//overloaded so that when inserted into BTree, it will store by end value
	//idk why this works
	public String toString() {
		//return endVertex.toString();
		return getFullArc();
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
