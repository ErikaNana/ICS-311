package dll;

public class Main {
	public static void main (String [] args) {
		DLinkedList<String> list = new DLinkedList<String>();
		String [] listValues = new String [4];
		listValues[0] = "bob";
		listValues[1] = "apple";
		listValues[2] = "dog";
		listValues[3] = "cat";

		for (int i = 0; i < listValues.length; i++) {
			list.insert(listValues[i]);
			System.out.println("inserting:  " + listValues[i]);
			System.out.println("list now:  " + list);
			System.out.println("");
		}
		
		System.out.println(list.traverseGetIndex("a"));
		System.out.println(list.traverseGetIndex("b"));
		System.out.println(list.traverseGetIndex("c"));
		System.out.println(list.traverseGetIndex("d"));
		
		list.remove("dog");
		list.remove("cat");
		list.remove("bob");
		list.remove("apple");
		System.out.println(list);
	}
}
