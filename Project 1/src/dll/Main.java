package dll;

public class Main {
	public static void main (String [] args) {
		DLinkedList<String> list = new DLinkedList<String>();
		String[] listValues = {"bob","storm","erika","kelsie","bree","georgia"};
		for (int i = 0; i < 5; i++) {
			list.insert(listValues[i], i);
		}
		System.out.println(list);
	}
}
