package skip;

import java.util.*;

import main.DynamicSet;

/** Code is adapted from the skip list from 
 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html 
 * */

public class SkipList<Type> implements DynamicSet<Type> {	
  public SkipListEntry<Type> head;    // First element of the top level
  public SkipListEntry<Type> tail;    // Last element of the top level
  public int size; 		// number of entries in the Skip list
  public int height;       // Height
  public Random r;    // Coin toss

  /* ----------------------------------------------
     Constructor: empty skiplist

                          null        null
                           ^           ^
                           |           |
     head --->  null <-- -inf <----> +inf --> null
                           |           |
                           v           v
                          null        null
     ---------------------------------------------- */
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


  /** Returns the number of entries in the hash table. */
  public int size(){ 
    return size; 
  }

  /** Returns whether or not the table is empty. */
  public boolean isEmpty() { 
    return (size == 0); 
  }

  /** Returns the value associated with a key. */
  @SuppressWarnings("unchecked")
public String getValueOfNode (String k) {
     SkipListEntry<Type> p;

     p = (SkipListEntry<Type>) search((Type) k);

     if ( k.equals( p.getKey() ) )
        return p.getKey();
     else
        return(null);
  }

  /* ------------------------------------------------------------------
     insertAfterAbove(p, q, y=(k,v) )
 
        1. create new entry (k,v)
	2. insert (k,v) AFTER p
	3. insert (k,v) ABOVE q

             p <--> (k,v) <--> p.right
                      ^
		      |
		      v
		      q

      Returns the reference of the newly created (k,v) entry
     ------------------------------------------------------------------ */
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

  public void printHorizontal(){
     String s = "";
     int i;

     SkipListEntry<Type> p;

     /* ----------------------------------
	Record the position of each entry
	---------------------------------- */
     p = head;

     while ( p.down != null ){
        p = p.down;
     }

     i = 0;
     while ( p != null ){
        p.pos = i++;
        p = p.right;
     }

     /* -------------------
	Print...
	------------------- */
     p = head;

     while ( p != null ){
        s = getOneRow( p );
	System.out.println(s);

        p = p.down;
     }
  }

  public String getOneRow( SkipListEntry<Type> p ){
     String s;
     int a, b, i;

     a = 0;

     s = "" + p.key;
     p = p.right;

     while ( p != null ){
    	 SkipListEntry<Type> q;
    	 q = p;
    	 while (q.down != null)
    	q = q.down;
        b = q.pos;

        s = s + " <-";


        for (i = a+1; i < b; i++)
        	s = s + "--------";
        	s = s + "> " + p.key;
        	a = b;
        	p = p.right;
     }

     return(s);
  }

  public void printVertical(){
     String s = "";
     SkipListEntry<Type> p;

     p = head;

     while ( p.down != null )
        p = p.down;

     while ( p != null ){
    	 s = getOneColumn( p );
	System.out.println(s);

        p = p.right;
     }
  }


  public String getOneColumn( SkipListEntry<Type> p ){
     String s = "";

     while ( p != null ){
        s = s + " " + p.key;
        p = p.up;
     }
     return(s);
  }


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
@Override
public Object minimum() {
	//System.out.println("head:  " + head.getKey());
	//System.out.println(head.right.getKey());

    SkipListEntry<Type> p;
    p = head.right;

    while (true){
       while ( p.left.key != SkipListEntry.negInf){
    	   p = p.left;
       	}
		if ( p.down != null ){ //go down one level if can
			//System.out.println("go down");
			p = p.down;
		}
	    else
		   break;	//reached the lowest level so exit
    }

    return p;	
}


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


@Override
public Object successor(Type key) {
	@SuppressWarnings("unchecked")
	SkipListEntry<Type> node = (SkipListEntry<Type>) search(key);
	return node.right;	
}


@Override
public Object predecessor(Type key) {
	@SuppressWarnings("unchecked")
	SkipListEntry<Type> node = (SkipListEntry<Type>) search(key);
	return node.left;
}

} 