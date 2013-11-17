package code;

import java.util.HashMap;

//data = attributes, annotations = weight, colors, parent

public class Vertex {

	private String key;
	private Object data;
	private HashMap<Object,Object> annotations;
	
	public Vertex(String key) {
		this.key = key;
		this.data = null;
		this.annotations = new HashMap<Object,Object>();
	}
	
	public Vertex(String key, Object data) {
		this.key = key;
		this.data = data;
		this.annotations = new HashMap<Object,Object>();
	}
	public String toString() {
		return key;
	}
	public String getKey() {
		return key;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	//just in case
	public void setValue(String key) {
		this.key = key;
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
