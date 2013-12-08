package code;

public class Utils {
		
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
			sort.sort(p,r);
			double end = System.nanoTime();
			runningTotal = runningTotal + (end - start);
		}
		results[0] = runningTotal/10;
		results[1] = sort.getFirstValue();
		results[2] = sort.getLastValue();
		
		//print the results
		String leftAlignFormat = "%-9s: %14.2f ns; First Key: ";
		System.out.println();
		System.out.format(leftAlignFormat,title, (Double) results[0]);
		System.out.printf("%-7s", (String) results[1]);
		System.out.print("; Last Key: ");
		System.out.printf("%-7s", (String) results[2]);
	}
}
