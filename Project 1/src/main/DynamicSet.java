package main;

public interface DynamicSet<Type> {
	  // ADT that stores and retrieves Objects according to keys  

    public int size();
    // Returns the number of elements currently in the set.

    public void insert(Type key, Object e); 
    // Inserts element e in the set under key k.

    public void delete(Type key); 
    // Given a key k, removes elements indexed by k from the set.

    public Object search(Type key); 
    // Finds an Object with key k and returns a pointer to it,
    // or null if not found. 

    // The following operations apply when there is a total ordering on KeyType   

    public Object minimum( ); 
    // Finds an Object that has the smallest key, and returns a pointer to it,
    // or null if the set is empty. 

    public Object maximum( ); 
    // Finds an Object that has the largest key, and returns a pointer to it,
    // or null if the set is empty.

    public Object successor(Type key); 
    // Finds an Object that has the next larger key in the set above k, 
    // and returns a pointer to it, or null if k is the maximum element.

    public Object predecessor(Type key); 
    // Finds an Object that has the next smaller key in the set below k,
    // and returns a pointer to it, or null if k is the minimum element.
}
