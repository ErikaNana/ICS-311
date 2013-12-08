package code;

public class Utils {
	
	//determine what sort to do 
	public static final int HEAP_SORT = 1;
	public static final int INSERTION_SORT = 2;
	public static final int MERGE_SORT = 3;
	public static final int QUICK_SORT = 4;
	
	public static int compare (String one, String two) {
		//if string is a number, convert to number
		if (one.matches("\\d+") && two.matches("\\d+")) {
			int uno = Integer.parseInt(one);
			int dos = Integer.parseInt(two);
			if (uno > dos) {
				return 1;
			}
			if (uno < dos) {
				return -1;
			}
			return 0;
		}
		if (one.equals("inf")) {
			return 1;
		}
		if (two.equals("inf")) {
			return -1;
		}
		if (one.equals("inf") && two.equals("inf")){
			return 0;
		}
		//else compares strings and as lower case
		else {
			if (one.compareToIgnoreCase(two) < 0) {
				return -1;
			}
			if (one.compareToIgnoreCase(two) > 0) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}
	
	public void printLine() {
		for (int i = 0; i < 54; i++) {
			System.out.print("-");
		}
		System.out.println("");
	}
	//run the sort 10 times, and return an array of all the times
	public void printResults(Sort sort, int p, int r, int type) {
		Object [] results = new Object [3];
		double runningTotal = 0;
		//sort and get output data
		for (int i = 0; i < 10; i++) {
			double start = System.nanoTime();
			switch (type) {
				case HEAP_SORT:
					sort.sort();
					break;
				case INSERTION_SORT:
					sort.sort();
					break;
				case MERGE_SORT:
					sort.sort(p,r);
					break;
				case QUICK_SORT:
					sort.sort(p,r);
					break;
			}
			double end = System.nanoTime();
			runningTotal = runningTotal + (end - start);
		}
		results[0] = runningTotal/10;
		results[1] = sort.getFirstValue();
		results[2] = sort.getLastValue();
		
		//build output
		printLine();
	}
}
