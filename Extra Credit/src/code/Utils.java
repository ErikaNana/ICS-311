package code;

public class Utils {	
    /**
     * Prints the divider
     */
    public static void printDivider() {
            for(int i = 0; i < 80; i++) {
                    System.out.print("-");
            }
            System.out.println("");
    }
	//run the sort 10 times, and return an array of all the times
	public static void sortAndPrint(Sort sort, int p, int r,String title) {
		Object [] results = new Object [3];
		double runningTotal = 0;
		for (int i = 0; i < 10; i++) {
			double start = System.nanoTime();
			Sort inputSort = sort;
			inputSort.sort(p, r);
			if (i == 0) { //do this only once
				results[1] = inputSort.getFirstValue();
				results[2] = inputSort.getLastValue();
			}
			inputSort = null;
			double end = System.nanoTime();
			runningTotal = runningTotal + (end - start);
		}
		results[0] = runningTotal/10;
		//print the results
		String leftAlignFormat = "%-9s: %14.2f ns; First Key: ";
		System.out.println();
		System.out.format(leftAlignFormat,title, (Double) results[0]);
		System.out.printf("%-7s", (String) results[1]);
		System.out.print("; Last Key: ");
		System.out.printf("%-7s", (String) results[2]);
	}
}
