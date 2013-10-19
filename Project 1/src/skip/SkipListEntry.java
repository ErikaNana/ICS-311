package skip;

/** Code is adapted from the skip list from 
 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html 
 * */

public class SkipListEntry<Type>{
  public String key;
  public int pos;      // I added this to print the skiplist "nicely"

  public SkipListEntry<Type> up, down, left, right;

  public static String negInf = new String("-oo");  // -inf key value
  public static String posInf = new String("+oo");  // +inf key value

  public SkipListEntry(String k){ 
     key = k;
     up = down = left = right = null;
  }

  public String getKey(){ 
	  return key; 
  }

  public String toString() {
    return key;
  }
}