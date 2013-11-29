package code;

import java.util.ArrayList;

public class Data {
	ArrayList<String> data;
	public Data() {
		data = new ArrayList<String>();
	}
	
	//pretty formatted data
	public String getData() {
		String returnString = new String();
		if (data.isEmpty()) {
			return null;
		}
		if (data.size() == 1) {
			return data.get(0);
		}
		for (int i= 0; i < data.size(); i++) {
			if(i == data.size() - 1) {
				return returnString.concat(data.get(i));
			}
			returnString = returnString.concat(data.get(i) + ", ");
		}
		return returnString;
	}
	
	public void addData(String attribute) {
		data.add(attribute);
	}
	public String toString() {
		return getData();
	}
}
