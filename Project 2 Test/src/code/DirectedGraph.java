package code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/** ADT for a directed graph where V is a set of vertices ands A a set of directed
 * edges (arcs) */

//separate structure for adjacency list (shows what's connected)
//separate structure for actual vertices and edges with annotations or whatever
	//this is hashmap of vertices and edges
public class DirectedGraph {

	AList aList;
	//BTree will insert by end vertex
	HashMap<Vertex,BTree<Arc>> map ; //since keys are strings
	HashMap<Object,ArrayList<Object>> annotation;
	ArrayList<Arc> arcs;	
	
	public DirectedGraph(){
		aList = new AList();
		map = new HashMap<Vertex,BTree<Arc>>();
		arcs = new ArrayList<Arc>();
	}
	/******************** ACCESSORS ******************************/
	
	//Returns the number of vertices
	public int numVertices() {
		return aList.getNumOfVertices();
	}
	
	//Returns the number of arcs
	public int numArcs() {
		return aList.getNumOfEdges();
	}
	
	//Returns an iterator over the vertices V
	public Iterator<Vertex> vertices() {
		return map.keySet().iterator();
	}
	
	//Returns an iterator over the arcs A of G
	public Iterator<Arc> arcs() {
		return new ArcIterator();
	}
	
	//getVertex
	public Vertex getVertex(Object key) {
		System.out.println("key:  " + key);
		Set<Vertex> keys = map.keySet();
		for (Iterator<Vertex> i = keys.iterator(); i.hasNext();) {
			Vertex currentKey = i.next();
			if (Utils.compareValue(currentKey.toString(), key.toString()) == Utils.EQUAL){
				System.out.println("returning match");
				return currentKey;
			}
		}
		return null;
	}
	
	    //Returns the Vertex associated with client key.
	
	//getArc
	//source and target will be strings
	public Arc getArc(Object source, Object target) {
		Vertex start = getVertex(source);
		Vertex end = getVertex(target);
		if (start != null && end != null) {
			BTree<Arc> tree = map.get(start);
			//dummy: just using it to check variables
			Arc arc = new Arc(start,end);
			return (Arc) tree.searchForValue(arc);
		}
		return null;
	}
	    //Returns the Arc that connects client keys source and target, or null if none.
	
	//getVertexData
	public Object getVertexData(Vertex v) {
		Vertex vertex = getVertex(v);
		return vertex.getData();
	}
	    //Returns the client data Object associated with Vertex v.
	
	//getArcData
	public Object getArcData(Arc a) {
		Arc arc = getArc(a.getStartVertex(), a.getEndVertex());
		return arc.getData();
	}
	    //Returns the client data Object associated with Arc a.
	
	//inDegree
	public int inDegree(Vertex v) {
		return aList.getInDegree(v);
	}
	    //Returns the number of arcs incoming to v.
	
	//outDegree
	public int outDegree(Vertex v) {
		return aList.getOutDegree(v);
	}
	    //Returns the number of arcs outgoing from v.
	
	//inAdjacentVertices
	public Iterator<Vertex> inAdjacentVertices(Vertex v) {
		ArrayList<Vertex> inVertices = new ArrayList<Vertex>();
		//get an arrayList for the iterator
		Set<Vertex> keys = map.keySet();
		for (Iterator<Vertex> i = keys.iterator(); i.hasNext();) {
			Vertex currentKey = i.next();
			BTree<Arc> arcs = map.get(currentKey);
			ArrayList<Arc> list = arcs.getListOfNodes();
			for (Arc arc: list) {
				if (arc.getEndVertex() == v) {
					inVertices.add(currentKey);
				}
			}
		}
		return new VertexIterator(inVertices);
	}
	    //Returns an iterator over the vertices adjacent to v by incoming arcs.
	
	//outAdjacentVertices
	public Iterator<Vertex> outAdjacentVertices(Vertex v) {
		ArrayList<Vertex> outVertices = new ArrayList<Vertex>();
		Set<Vertex> keys = map.keySet();
		for (Iterator<Vertex> i = keys.iterator(); i.hasNext();) {
			Vertex currentKey = i.next();
			if (currentKey == v) {
				BTree<Arc> arcs = map.get(currentKey);
				ArrayList<Arc> list = arcs.getListOfNodes();
				for (Arc arc: list) {
					outVertices.add(arc.getEndVertex());
				}
			}
		}
		return new VertexIterator(outVertices);
	}
	    //Returns an iterator over the vertices adjacent to v by outgoing arcs.
	
	//origin
	public Vertex origin(Arc a) {
		return a.getStartVertex();
	}
	    //Returns the origin (start); vertex of Arc a.
	
	
	public Vertex destination(Arc a) {
		return a.getEndVertex();
	}
	    //Returns the destination (end); vertex of Arc a.
	
	/******************** MUTATORS ******************************/
	
	//can only store Strings as keys
	public Vertex insertVertex(Object key) {
		Vertex vertex = new Vertex((String) key);
		BTree<Arc> tree = new BTree<Arc>();
		map.put(vertex, tree);
		aList.addVertex(vertex);
		return vertex;
	}
	
	public Vertex insertVertex(Object key, Object data) {
		Vertex vertex = new Vertex((String) key, data);
		BTree<Arc> tree = new BTree<Arc>();
		map.put(vertex, tree);
		aList.addVertex(vertex);
		return vertex;
	}
	    //Inserts a new isolated vertex indexed under (retrievable via); key and optionally containing an object data (which defaults to null);.
	    //Returns the new Vertex.
	
	//insertArc
	//store the arc under the start vertex
	public Arc insertArc(Vertex u, Vertex v) {
		Arc arc = new Arc(u,v);
		BTree<Arc> tree = map.get(u);
		tree.insert(arc, null);
		map.put(u, tree);
		aList.addEdge(u, v);
		arcs.add(arc);
		return arc;
	}
	public Arc insertArc(Vertex u, Vertex v, Object data) {
		Arc arc = new Arc(u,v,data);
		BTree<Arc> tree = map.get(u);
		tree.insert(arc, null);
		map.put(u, tree);
		aList.addEdge(u, v);
		arcs.add(arc);
		return arc;
	}
	    //Inserts a new arc from an existing vertex to another, optionally containing an object data.
	    //Returns the new Arc.
	
	//setVertexData
	public void setVertexData(Vertex v, String data) {
		Vertex vertex = getVertex(v);
		vertex.addData(data);
	}
	    //Changes the data Object associated with Vertex v to data.
	
	//setArcData
	public void setArcData(Arc a, Object data) {
		Arc arc = getArc(a.getStartVertex(), a.getEndVertex());
		arc.setData(data);
	}
	    //Changes the data Object associated with Arc a to data.
	
	//removeVertex	
	public Object removeVertex(Vertex v) {
		if (map.containsKey(v)) {
			//remove from edges
			for (BTree<Arc> tree: map.values()) {
				if (!tree.isEmpty()) {
					//dummy arc
					Vertex dummy = new Vertex("dummy");
					Arc arc = new Arc(dummy,v);
					Arc foundArc = (Arc) tree.searchForValue(arc);
					if (foundArc != null) {
						tree.delete(foundArc);
						map.put(v, tree);
					}
				}
			}
			map.remove(v);
			//remove from aList
			aList.deleteVertex(v);
			return v;
		}
		return null;
	}
	//Deletes a vertex and all its incident arcs (and edges under the undirected extension);.
	//Returns the client data object formerly stored at v.
	
	//removeArc
	public Object removeArc(Arc a) {
		Vertex key = a.getStartVertex();
		Set<Vertex> keys = map.keySet();
		for (Iterator<Vertex> i = keys.iterator(); i.hasNext();) {
			Vertex currentKey = i.next();
			if (Utils.compareValue(currentKey.toString(), key.toString()) == Utils.EQUAL){
				BTree<Arc> tree = map.get(currentKey);
				tree.delete(a);
				Vertex start = a.getStartVertex();
				Vertex end = a.getEndVertex();
				aList.deleteEdge(start, end);
				return a;
			}
		}
		return null;
	}
	    //Removes an arc.
	    //Returns the client data object formerly stored at a.
	
	//reverseDirection
	public void reverseDirection(Arc a) {
		//delete and update
		Arc arc = a;
		Vertex start = a.getStartVertex();
		Vertex end = a.getEndVertex();

		this.removeArc(a);
		if (arc.getData() != null) {
			this.insertArc(end, start,arc.getData());
		}
		else {
			this.insertArc(end, start);
		}
	} 
	    //Reverse the direction of an arc.
	
	/************  ANNOTATORS AND DECORATORS ************/
	
	//These are how you annotate or "decorate" a graph temporarily during the run of an algorithm. 
	//By mapping keys to values on each object, we provide a general facility that 
	//makes multiple annotations possible. 
	//For example, you can annotate a node under keys "COLOR" and "PARENT" simultaneously.
	
	//setAnnotation
	//change to var args?
	//annotation is hashed to itself
	public void setAnnotation(Vertex v, Object k, Object o) {
		v.setAnnotation(k, o);
	} 
	public void setAnnotation(Arc a, Object k, Object o) {
		a.setAnnotation(k, o);
	} 
	
	//Annotates a vertex v or arc a with object o indexed by key k.
	
	//getAnnotation
	public Object getAnnotation(Vertex v, Object k) {
		return v.getAnnotation(k);
	} 
	public Object getAnnotation(Arc a, Object k) {
		return a.getAnnotation(k);
	} 
	    //Returns the object indexed by k annotating a vertex v or arc a.
	
	//removeAnnotation
	public Object removeAnnotation(Vertex v, Object k) {
		Object annotation = v.getAnnotation(k);
		v.removeAnnotation(k);
		return annotation;
	} 
	public Object removeAnnotation(Arc a, Object k) {
		Object annotation = a.getAnnotation(k);
		a.removeAnnotation(k);
		return annotation;
	} 
	    //Removes the annotation on a vertex v or arc a indexed by k and returns it.
	
	//clearAnnotations
	public void clearAnnotations(Object k) {
		if (k instanceof Arc) {
			((Arc) k).clearAnnotations();
		}
		else if (k instanceof Vertex) {
			((Vertex) k).clearAnnotations();
		}
	} 
    //Removes all annotations on vertices or arcs indexed by k. Use this to clean up between runs.
	class ArcIterator implements Iterator<Arc>{
		int currentIndex = 0;
		
		@Override
		public boolean hasNext() {
			if (currentIndex >= arcs.size()) {
				return false;
			}
			return true;
		}

		@Override
		public Arc next() {
			return arcs.get(currentIndex++);
		}

		@Override
		public void remove() {
			arcs.remove(--currentIndex);
		}
		
	}
	class VertexIterator implements Iterator<Vertex>{
		int currentIndex = 0;
		ArrayList<Vertex> list;
		public VertexIterator(ArrayList<Vertex> list) {
			this.list = list;
		}
		@Override
		
		public boolean hasNext() {
			if (currentIndex >= list.size()) {
				return false;
			}
			else {
				return true;
			}
		}

		@Override
		public Vertex next() {
			return list.get(currentIndex++);		
		}
		

		@Override
		public void remove() {
			list.remove(--currentIndex);
		}
		
	}
	public AList getAList() {
		return aList;
	}
}
