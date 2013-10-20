package main;


/**
 * The Interface DynamicSet.  Simulates an ADT.
 *
 * @author Daniel Suthers
 * @param <Type> the generic type
 */
public interface DynamicSet<Type> {
    /**
  	 * @return The size of the set.
  	 */
  	public int size();

    /**
     * Inserts the element e in the set under key k.
     *
     * @param key The key to insert.
     * @param e The object to insert.
     */
    public void insert(Type key, Object e); 
    
    /**
     * Given a key k, removes elements indexed by k from the set.
     *
     * @param key The key to delete
     */
    public void delete(Type key); 

    /**
     * Finds an Object with key k and returns a pointer to it, or null if
     * not found.
     *
     * @param key The key to search.
     * @return The object with key k.
     */
    public Object search(Type key); 

    // The following operations apply when there is a total ordering on KeyType   

    /**
     * Finds an Object that has the smallest key, and returns a pointer to it,
     * or null if the set is empty.
     *
     * @return The minimum object in the set.
     */
    public Object minimum( ); 

    /**
     * Finds an Object that has the largest key, and returns a pointer to it, or null
     * if the set is empty.
     *
     * @return the Object with the largest key
     */
    public Object maximum( ); 

    /**
     * Finds an Object that has the next larger key in the set above k, 
     * and returns a pointer to it, or null if k is the maximum element.
     *
     * @param key The key of the node to find the successor of.
     * @return the Object that is the successor of that key.
     */
    public Object successor(Type key); 


    /**
     * Finds an Object that has the next smaller key in the set below k,
     * and returns a pointer to it, or null if k is the minimum element.
     *
     * @param key The key of the node to find the predecessor of.
     * @return the Object that is the predecessor of that key.
     */
    public Object predecessor(Type key); 

}
