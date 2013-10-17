package skip;

//need the v for the sentinels!!!
//otherwise if not the sentinel just assume that the key value is the value

public class SkipListEntry<Type>{
  public String key;
  public Type value;

  public int pos;      // I added this to print the skiplist "nicely"

  public SkipListEntry<Type> up, down, left, right;

  public static String negInf = new String("-oo");  // -inf key value
  public static String posInf = new String("+oo");  // +inf key value

  public SkipListEntry(String k, Type v){ 
     key = k;
     value = v;

     up = down = left = right = null;
  }

  public Type getValue(){ 
    return value; 
  }

  public String getKey(){ 
	  return key; 
  }

  public Type setValue(Type val){
    Type oldValue = value;
    value = val;
    return oldValue;
  }

  @SuppressWarnings("unchecked")
public boolean equals(Object o) {
    SkipListEntry<Type> ent;
    try { 
      ent = (SkipListEntry<Type>) o;    // Test if o is a SkipListEntry...
    }
    catch (ClassCastException ex) 
    { 
	return false; 
    }
    return (ent.getKey() == key) && (ent.getValue() == value);
  }

  public String toString() {
    return "(" + key + "," + value + ")";
  }
}