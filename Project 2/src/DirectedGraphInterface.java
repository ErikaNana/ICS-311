import java.util.Iterator;

/** ADT for a directed graph where V is a set of vertices ands A a set of directed
 * edges (arcs) */

public interface DirectedGraphInterface<Type> {

/******************** ACCESSORS ******************************/

//Returns the number of vertices
public int numVertices();

//Returns the number of arcs
public int numArcs();

//Returns an iterator over the vertices V
public Iterator<Type> vertices();

//Returns an iterator over the arcs A of G
public Iterator<Type> arcs();

//getVertex
public Vertex getVertex(Object key);
    //Returns the Vertex associated with client key.

//getArc
public Arc getArc(Object source, Object target);
    //Returns the Arc that connects client keys source and target, or null if none.

//getVertexData
public Object getVertexData(Vertex v);
    //Returns the client data Object associated with Vertex v.

//getArcData
public Object getArcData(Arc a);
    //Returns the client data Object associated with Arc a.

//inDegree
public int inDegree(Vertex v);
    //Returns the number of arcs incoming to v.

//outDegree
public int outDegree(Vertex v);
    //Returns the number of arcs outgoing from v.

//inAdjacentVertices
public Iterator<Type> inAdjacentVertices(Vertex v);
    //Returns an iterator over the vertices adjacent to v by incoming arcs.

//outAdjacentVertices
public Iterator<Type> outAdjacentVertices(Vertex v);
    //Returns an iterator over the vertices adjacent to v by outgoing arcs.

//origin
public Vertex origin(Arc a);
    //Returns the origin (start); vertex of Arc a.


public Vertex destination(Arc a);
    //Returns the destination (end); vertex of Arc a.

/******************** MUTATORS ******************************/


public Vertex insertVertex(Object key);
public Vertex insertVertex(Object key, Object data);
    //Inserts a new isolated vertex indexed under (retrievable via); key and optionally containing an object data (which defaults to null);.
    //Returns the new Vertex.

//insertArc
public Arc insertArc(Vertex u, Vertex v);
public Arc insertArc(Vertex u, Vertex v, Object data);
    //Inserts a new arc from an existing vertex to another, optionally containing an object data.
    //Returns the new Arc.

//setVertexData
public void setVertexData(Vertex v, Object data);
    //Changes the data Object associated with Vertex v to data.

//setArcData
public void setArcData(Arc a, Object data);
    //Changes the data Object associated with Arc a to data.

//removeVertex
public Object removeVertex(Vertex v);
    //Deletes a vertex and all its incident arcs (and edges under the undirected extension);.
    //Returns the client data object formerly stored at v.

//removeArc
public Object removeArc(Arc a);
    //Removes an arc.
    //Returns the client data object formerly stored at a.

//reverseDirection
public void reverseDirection(Arc a); 
    //Reverse the direction of an arc.

/************  ANNOTATORS AND DECORATORS ************/

//These are how you annotate or "decorate" a graph temporarily during the run of an algorithm. By mapping keys to values on each object, we provide a general facility that makes multiple annotations possible. For example, you can annotate a node under keys "COLOR" and "PARENT" simultaneously.

//setAnnotation
public void setAnnotation(Vertex v, Object k, Object o); 
public void setAnnotation(Arc a, Object k, Object o); 
    //Annotates a vertex v or arc a with object o indexed by key k.

//getAnnotation
public Object getAnnotation(Vertex v, Object k); 
public Object getAnnotation(Arc a, Object k); 
    //Returns the object indexed by k annotating a vertex v or arc a.

//removeAnnotation
public Object removeAnnotation(Vertex v, Object k); 
public Object removeAnnotation(Arc a, Object k); 
    //Removes the annotation on a vertex v or arc a indexed by k and returns it.

//clearAnnotations
public void clearAnnotations(Object k); 
    //Removes all annotations on vertices or arcs indexed by k. Use this to clean up between runs.
}
