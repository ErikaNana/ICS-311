package skip;

/** Code is adapted from
 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html 
 * */

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
		System.out.println("predecessor of AAA:  " + ((SkipListEntry<String>) S.predecessor("AAA")));
		System.out.println("predecessor of KLM:  " + ((SkipListEntry<String>) S.predecessor("KLM")));
		System.out.println("successor of KLM:  " + ((SkipListEntry<String>) S.successor("KLM")));
		System.out.println("successor of AAA:  " + ((SkipListEntry<String>) S.successor("AAA")));
		System.out.println("maximum of list:  " + ((SkipListEntry<String>) S.maximum()));
		System.out.println("minimum of list:  " + ((SkipListEntry<String>) S.minimum()));
		
		//Test search
		System.out.print("Searching for ABC:  ");
		System.out.println(((SkipListEntry<String>) S.search("ABC")));;
		//Test delete
		System.out.println("deleting DEF");
		S.delete("DEF");
		S.printHorizontal();
		System.out.println("------");
	}
}