package code;

public class Vertex {

	private String key;
	private Object data;
	private Object anOne;
	private Object anTwo;
	
	public Vertex(String key) {
		this.key = key;
		this.data = null;
	}
	
	public Vertex(String key, Object data) {
		this.key = key;
		this.data = data;
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
