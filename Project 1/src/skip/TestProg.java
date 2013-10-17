package skip;
public class TestProg
{
//inserts 4 entries
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

		S.insert("DEF", "123");
		S.printHorizontal();
		System.out.println("------");
		//    S.printVertical();
		//    System.out.println("======");

		S.insert("KLM", "123");
		S.printHorizontal();
		System.out.println("------");
		//    S.printVertical();
		//    System.out.println("======");

		S.insert("HIJ", "123");
		S.printHorizontal();
		System.out.println("------");
		//    S.printVertical();
		//    System.out.println("======");

		S.insert("GHJ", "123");
		S.printHorizontal();
		System.out.println("------");
		//    S.printVertical();
		//    System.out.println("======");

		S.insert("AAA", "123");
		S.printHorizontal();
		System.out.println("------");
		//    S.printVertical();
		//    System.out.println("======");
	}
}