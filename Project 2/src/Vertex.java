
public class Vertex {

	private String key = null;
	
	public Vertex(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return key;
	}
	
	//just in case
	public void setValue(String key) {
		this.key = key;
	}
	
	public String toString() {
		return key;
	}
}
