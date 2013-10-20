package dll;

// TODO: Auto-generated Javadoc
/**
 * The Class TestDLL.
 */
public class TestDLL {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
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
		}
		//Test search
		System.out.println(((DNode<String>) list.search("bob")).getValue());
		System.out.println(((DNode<String>) list.search("apple")).getValue());
		System.out.println(((DNode<String>) list.search("cat")).getValue());
		System.out.println(((DNode<String>) list.search("dog")).getValue());
		
		//Test minimum and maximum
		System.out.println("maximum:  " + ((DNode<String>) list.maximum()).getValue());
		System.out.println("minimum:  " + ((DNode<String>) list.minimum()).getValue());
		
		//Test predecessor and successor
		System.out.println("predecessor of apple:  " + ((DNode<String>) list.predecessor("apple")).getValue());
		System.out.println("sucessor of dog:  " + ((DNode<String>) list.successor("dog")).getValue());
		System.out.println("predecessor of cat:  " + ((DNode<String>) list.predecessor("cat")).getValue());
		System.out.println("sucessor of cat:  " + ((DNode<String>) list.successor("cat")).getValue());
		
		//Test delete
		list.delete("dog");
		list.delete("cat");
		list.delete("bob");
		list.delete("apple");
		
		System.out.println("List now:  " + list.size());
	}
}
