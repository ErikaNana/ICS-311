package dll;

public class TestDLL {
	@SuppressWarnings("unchecked")
	public static void main (String [] args) {
		DLinkedList<String> list = new DLinkedList<String>();
		String [] listValues = new String [4];
		listValues[0] = "bob";
		listValues[1] = "apple";
		listValues[2] = "dog";
		listValues[3] = "cat";

		//Test insert
		for (int i = 0; i < listValues.length; i++) {
			DNode<String> node = new DNode<String>(listValues[i]);
			list.insert(listValues[i], node);
			System.out.println("inserting:  " + listValues[i]);
			System.out.println("list now:  " + list);
			System.out.println("");
		}
		
		System.out.println(((DNode<String>) list.search("bob")).getValue());
		System.out.println(((DNode<String>) list.search("apple")).getValue());
		System.out.println(((DNode<String>) list.search("cat")).getValue());
		System.out.println(((DNode<String>) list.search("dog")).getValue());
	
		System.out.println("maximum:  " + ((DNode<String>) list.maximum()).getValue());
		System.out.println("minimum:  " + ((DNode<String>) list.minimum()).getValue());
		
		//Test predecessor and successor
		System.out.println("predecessor of bob:  " + ((DNode<String>) list.predecessor("bob")).getValue());
		System.out.println("sucessor of bob:  " + ((DNode<String>) list.successor("bob")).getValue());
		
		//Test delete
		list.delete("dog");
		list.delete("cat");
		list.delete("bob");
		list.delete("apple");
		
		System.out.println("List now:  " + list.size());
	}
}
