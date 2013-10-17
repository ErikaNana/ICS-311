package skip;
public class TestProg
{
//inserts 4 entries
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		SkipList<String> S = new SkipList<String>();
		S.printHorizontal();
		System.out.println("------");
		//    S.printVertical();
		//    System.out.println("======");

		S.insert("ABC", "123");
		System.out.println(((SkipListEntry<String>) S.search("ABC")).getValue());
		S.printHorizontal();
		System.out.println("------");
		//    S.printVertical();
		//    System.out.println("======");

		S.insert("DEF", "456");
		S.printHorizontal();
		System.out.println("------");
		//    S.printVertical();
		//    System.out.println("======");

		S.insert("KLM", "789");
		S.printHorizontal();
		System.out.println("------");
		//    S.printVertical();
		//    System.out.println("======");

		S.insert("HIJ", "101112");
		S.printHorizontal();
		System.out.println("------");
		//    S.printVertical();
		//    System.out.println("======");

		S.insert("GHJ", "111213");
		S.printHorizontal();
		System.out.println("------");
		//    S.printVertical();
		//    System.out.println("======");

		S.insert("AAA", "121314");
		S.printHorizontal();
		System.out.println("------");
		//    S.printVertical();
		//    System.out.println("======");
		
		//Test successor and predecessor
		System.out.println("predecessor:  " + ((SkipListEntry<String>) S.predecessor("AAA")).getValue());
		System.out.println("successor:  " + ((SkipListEntry<String>) S.successor("AAA")).getValue());
		System.out.println("maximum of list:  " + ((SkipListEntry<String>) S.maximum()).getValue());
		System.out.println("minimum of list:  " + ((SkipListEntry<String>) S.minimum()).getValue());
	}
}