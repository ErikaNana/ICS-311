package code;

public class Vertex {

	private String key;
	private Object data;
	
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
}
