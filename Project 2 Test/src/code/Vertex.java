package code;

import java.util.HashMap;

//data = attributes, annotations = weight, colors, parent

public class Vertex {

	private String key;
	private Data dataList;
	private HashMap<Object,Object> annotations;
	
	public Vertex(String key) {
		this.key = key;
		this.annotations = new HashMap<Object,Object>();
		this.dataList = new Data();
	}
	
	public Vertex(String key, String data) {
		this.key = key;
		this.dataList = new Data();
		this.dataList.setData(data);
		this.annotations = new HashMap<Object,Object>();
	}
	public String toString() {
		return key;
	}
	public String getKey() {
		return key;
	}
	
	public Object getData() {
		return dataList.getData();
	}
	public void setData(String data) {
		this.dataList.setData(data);
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
