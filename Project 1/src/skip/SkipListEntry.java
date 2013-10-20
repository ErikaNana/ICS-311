package skip;

// TODO: Auto-generated Javadoc
/**
 * Code is adapted from the skip list from
 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html
 *
 * @param <Type> the generic type
 */

public class SkipListEntry<Type>{
  
  /** The key. */
  public String key;
  
  /** The pos. */
  public int pos;      // I added this to print the skiplist "nicely"

  /** The right. */
  public SkipListEntry<Type> up, down, left, right;

  /** The neg inf. */
  public static String negInf = new String("-oo");  // -inf key value
  
  /** The pos inf. */
  public static String posInf = new String("+oo");  // +inf key value

  /**
   * Instantiates a new skip list entry.
   *
   * @param k the k
   */
  public SkipListEntry(String k){ 
     key = k;
     up = down = left = right = null;
  }

  /**
   * Gets the key.
   *
   * @return the key
   */
  public String getKey(){ 
	  return key; 
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return key;
  }
}