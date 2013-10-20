package skip;

import java.util.*;

import main.DynamicSet;


/**
 * Code is adapted from the skip list from
 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html
 *
 * @param <Type> the generic type
 */

public class SkipList<Type> implements DynamicSet<Type> {	
  
  /** The first element of the top level */
  public SkipListEntry<Type> head;   
  
  /** Last element of the top level. */
  public SkipListEntry<Type> tail;    
  
  /** The number of entries in the Skip list. */
  public int size; 		
  
  /** The height. */
  public int height;       
  
  /** The random generator that simulates a coin toss */
  public Random r;    

  /**
   * Instantiates a new skip list.
   */
  public SkipList(){ //default constructor 
     SkipListEntry<Type> p1, p2;

     p1 = new SkipListEntry<Type>(SkipListEntry.negInf);
     p2 = new SkipListEntry<Type>(SkipListEntry.posInf);

     head = p1;
     tail = p2;

     p1.right = p2;
     p2.left = p1;

     size = 0;
     height = 0;

     r = new Random();
  }


  /**
   * Returns the number of entries.
   *
   * @return the int
   */
  public int size(){ 
    return size; 
  }

  /**
   * Returns whether or not the table is empty.
   *
   * @return True, if is empty
   */
  public boolean isEmpty() { 
    return (size == 0); 
  }

  /**
   * Insert after above.
   *
   * @param p The entry that the entry needs to be inserted after
   * @param q The entry that the entry needs to be inserted above
   * @param k The key
   * @return The updated SkipList entry
   */
  public SkipListEntry<Type> insertAfterAbove(SkipListEntry<Type> p, SkipListEntry<Type> q, 
                                         String k)
  {
     SkipListEntry<Type> e;

     e = new SkipListEntry<Type>(k);

     /* ---------------------------------------
	Use the links before they are changed !
	--------------------------------------- */
     e.left = p;
     e.right = p.right;
     e.down = q;

     /* ---------------------------------------
	Now update the existing links..
	--------------------------------------- */
     p.right.left = e;
     p.right = e;
     q.up = e;

     return(e);
  }

	/* (non-Javadoc)
	 * @see main.DynamicSet#insert(java.lang.Object, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void insert(Type key, Object value) {
	    SkipListEntry<Type> p, q;
	    int i;
	
	    p = (SkipListEntry<Type>) search(key);
	
	    /* ------------------------
		Insert new entry (k,v)
		------------------------ */
	
	    /* ------------------------------------------------------
	       **** BUG: He forgot to insert in the lowest level !!!
		Link at the lowest level
		------------------------------------------------------ */
	    q = new SkipListEntry<Type>((String)key);
	    q.left = p;
	    q.right = p.right;
	    p.right.left = q;
	    p.right = q;
	
	    i = 0;                   // Current level = 0
	
	    while ( r.nextDouble() < 0.5 ){
		// Coin flip success: make one more level....
	    	
		/* ---------------------------------------------
		   Check if height exceed current height.
		   If so, make a new EMPTY level
		   --------------------------------------------- */
	       if ( i >= height ){
	          SkipListEntry<Type> p1, p2;
	
	          height = height + 1;
	          p1 = new SkipListEntry<Type>(SkipListEntry.negInf);
	          p2 = new SkipListEntry<Type>(SkipListEntry.posInf);
		   
			   p1.right = p2;
			   p1.down  = head;
		
			   p2.left = p1;
			   p2.down = tail;
		
			   head.up = p1;
			   tail.up = p2;
		
			   head = p1;
			   tail = p2;
		}
	
	
			/* -------------------------
			   Scan backwards...
			   ------------------------- */
			while ( p.up == null ){
			   p = p.left;
			}
			p = p.up;
	
	
			/* ---------------------------------------------
		          Add one more (k,v) to the column
			   --------------------------------------------- */
		  	SkipListEntry<Type> e;
		  		 
		  	e = new SkipListEntry<Type>((String) key);  // Don't need the value...
		  		 
		  	/* ---------------------------------------
		  	   Initialize links of e
		  	   --------------------------------------- */
		  	e.left = p;
		  	e.right = p.right;
		  	e.down = q;
		  		 
		  	/* ---------------------------------------
		  	   Change the neighboring links..
		  	   --------------------------------------- */
		  	p.right.left = e;
		  	p.right = e;
		  	q.up = e;
		  	q = e;		// Set q up for the next iteration
		  	i++;	// Current level increased by 1
	    }
	    size++;
	}
	
	
	/* (non-Javadoc)
	 * @see main.DynamicSet#delete(java.lang.Object)
	 */
	@Override
	public void delete(Type key) {
		@SuppressWarnings("unchecked")
		SkipListEntry<Type> p = (SkipListEntry<Type>) search(key);
		
		while (p != null) {
			p.left.right = p.right;
			p.right.left = p.left;
			//travel up the tower if can
			p = p.up;
		}
		size--;
	}
	
	
	/* (non-Javadoc)
	 * @see main.DynamicSet#search(java.lang.Object)
	 */
	@Override
	public Object search(Type key) {
	    SkipListEntry<Type> p;
	    
	    /* -----------------
		Start at "head"
		----------------- */
	    p = head;
	
	    while (true){
	       /* --------------------------------------------
		   Search RIGHT until you find a LARGER entry
	
	          E.g.: k = 34
	
	                    10 ---> 20 ---> 30 ---> 40
	                                     ^
	                                     |
	                                     p stops here
			p.right.key = 40
		   -------------------------------------------- */
	    	while ( p.right.key != SkipListEntry.posInf && 
				p.right.key.compareToIgnoreCase((String) key) <= 0 ){
		        p = p.right;
	    	}
	
			/* ---------------------------------
			   Go down one level if you can...
			   --------------------------------- */
			if ( p.down != null ){
				p = p.down;
			}
	       else
		   break;	// We reached the LOWEST level... Exit...
	    }
	
	    return p;	// p.key <= k
	}
	
	//go all the way down to the bottom level
	/* (non-Javadoc)
	 * @see main.DynamicSet#minimum()
	 */
	@Override
	public Object minimum() {
	    SkipListEntry<Type> p;
	    p = head.right;
	
	    while (true){
	       while ( p.left.key != SkipListEntry.negInf){
	    	   p = p.left;
	       	}
			if ( p.down != null ){ //go down one level if can
				p = p.down;
			}
		    else
			   break;	//reached the lowest level so exit
	    }
	
	    return p;	
	}
	
	
	/* (non-Javadoc)
	 * @see main.DynamicSet#maximum()
	 */
	@Override
	public Object maximum() {
	    SkipListEntry<Type> p;
	    p = head;
	
	    while (true){
	       while ( p.right.key != SkipListEntry.posInf){
	    	   p = p.right;
	       	}
	       
			if ( p.down != null ){ //go down one level if can
				p = p.down;
			}
		    else
			   break;	//reached the lowest level so exit
	    }
	
	    return p;	
	}
	
	
	/* (non-Javadoc)
	 * @see main.DynamicSet#successor(java.lang.Object)
	 */
	@Override
	public Object successor(Type key) {
		@SuppressWarnings("unchecked")
		SkipListEntry<Type> node = (SkipListEntry<Type>) search(key);
		return node.right;	
	}
	
	
	/* (non-Javadoc)
	 * @see main.DynamicSet#predecessor(java.lang.Object)
	 */
	@Override
	public Object predecessor(Type key) {
		@SuppressWarnings("unchecked")
		SkipListEntry<Type> node = (SkipListEntry<Type>) search(key);
		return node.left;
	}

} 