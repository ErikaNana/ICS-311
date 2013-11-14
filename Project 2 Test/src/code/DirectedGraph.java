package code;
import java.util.Iterator;

/** ADT for a directed graph where V is a set of vertices ands A a set of directed
 * edges (arcs) */

public class DirectedGraph<Type> {

/******************** ACCESSORS ******************************/

//Returns the number of vertices
public int numVertices() {
	return 0;
}

//Returns the number of arcs
public int numArcs() {
	return 0;
}

//Returns an iterator over the vertices V
public Iterator<Type> vertices() {
	return null;
}

//Returns an iterator over the arcs A of G
public Iterator<Type> arcs() {
	return null;
}

//getVertex
public Vertex getVertex(Object key) {
	return null;
}
    //Returns the Vertex associated with client key.

//getArc
public Arc getArc(Object source, Object target) {
	return null;
}
    //Returns the Arc that connects client keys source and target, or null if none.

//getVertexData
public Object getVertexData(Vertex v) {
	return null;
}
    //Returns the client data Object associated with Vertex v.

//getArcData
public Object getArcData(Arc a) {
	return null;
}
    //Returns the client data Object associated with Arc a.

//inDegree
public int inDegree(Vertex v) {
	return 0;
}
    //Returns the number of arcs incoming to v.

//outDegree
public int outDegree(Vertex v) {
	return 0;
}
    //Returns the number of arcs outgoing from v.

//inAdjacentVertices
public Iterator<Type> inAdjacentVertices(Vertex v) {
	return null;
}
    //Returns an iterator over the vertices adjacent to v by incoming arcs.

//outAdjacentVertices
public Iterator<Type> outAdjacentVertices(Vertex v) {
	return null;
}
    //Returns an iterator over the vertices adjacent to v by outgoing arcs.

//origin
public Vertex origin(Arc a) {
	return null;
}
    //Returns the origin (start); vertex of Arc a.


public Vertex destination(Arc a) {
	return null;
}
    //Returns the destination (end); vertex of Arc a.

/******************** MUTATORS ******************************/


public Vertex insertVertex(Object key) {
	return null;
}
public Vertex insertVertex(Object key, Object data) {
	return null;
}
    //Inserts a new isolated vertex indexed under (retrievable via); key and optionally containing an object data (which defaults to null);.
    //Returns the new Vertex.

//insertArc
public Arc insertArc(Vertex u, Vertex v) {
	return null;
}
public Arc insertArc(Vertex u, Vertex v, Object data) {
	return null;
}
    //Inserts a new arc from an existing vertex to another, optionally containing an object data.
    //Returns the new Arc.

//setVertexData
public void setVertexData(Vertex v, Object data) {
}
    //Changes the data Object associated with Vertex v to data.

//setArcData
public void setArcData(Arc a, Object data) {
}
    //Changes the data Object associated with Arc a to data.

//removeVertex
public Object removeVertex(Vertex v) {
	return null;
}
    //Deletes a vertex and all its incident arcs (and edges under the undirected extension);.
    //Returns the client data object formerly stored at v.

//removeArc
public Object removeArc(Arc a) {
	return null;
}
    //Removes an arc.
    //Returns the client data object formerly stored at a.

//reverseDirection
public void reverseDirection(Arc a) {
} 
    //Reverse the direction of an arc.

/************  ANNOTATORS AND DECORATORS ************/

//These are how you annotate or "decorate" a graph temporarily during the run of an algorithm. By mapping keys to values on each object, we provide a general facility that makes multiple annotations possible. For example, you can annotate a node under keys "COLOR" and "PARENT" simultaneously.

//setAnnotation
public void setAnnotation(Vertex v, Object k, Object o) {
} 
public void setAnnotation(Arc a, Object k, Object o) {
} 
    //Annotates a vertex v or arc a with object o indexed by key k.

//getAnnotation
public Object getAnnotation(Vertex v, Object k) {
	return null;
} 
public Object getAnnotation(Arc a, Object k) {
	return null;
} 
    //Returns the object indexed by k annotating a vertex v or arc a.

//removeAnnotation
public Object removeAnnotation(Vertex v, Object k) {
	return null;
} 
public Object removeAnnotation(Arc a, Object k) {
	return null;
} 
    //Removes the annotation on a vertex v or arc a indexed by k and returns it.

//clearAnnotations
public void clearAnnotations(Object k) {
} 
    //Removes all annotations on vertices or arcs indexed by k. Use this to clean up between runs.
}
