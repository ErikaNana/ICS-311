/** 
 * Allows the user to run tests on the three data structures at one time.
 * Also supports individual operations on the data structures.
 * 
 * @author Original Author    Shun Yan Cheung
 * @author Derivative Author  Erika Nana
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