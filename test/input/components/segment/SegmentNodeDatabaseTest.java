/**
* This class tests the SegmentNodeDatabase class and its methods.
*
*
* @author Flynn Nisbet & Jalen Livingston
* @date 09-07-2023
*/

package input.components.segment;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import input.components.point.PointNode;

class SegmentNodeDatabaseTest
{
    public SegmentNodeDatabase build()
    {
    	//      A                                 
    	//     / \                                
    	//    B___C                               
    	//   / \ / \                              
    	//  /   X   \ 
    	// D_________E
    	//
		//
    	PointNode a = new PointNode("A", 3, 6);
    	PointNode b = new PointNode("B", 2, 4);
    	PointNode c = new PointNode("C", 4, 4);

    	PointNode d = new PointNode("D", 0, 0);
    	PointNode e = new PointNode("E", 6, 0);
    	PointNode x = new PointNode("X", 3, 3);

    	SegmentNodeDatabase db = new SegmentNodeDatabase();
    	  	
    	db.addUndirectedEdge(a, b);
    	db.addUndirectedEdge(a, c);
    	db.addUndirectedEdge(b, c);
    	db.addUndirectedEdge(b, x);
    	db.addUndirectedEdge(b, d);
    	db.addUndirectedEdge(c, x);
    	db.addUndirectedEdge(c, e);
    	db.addUndirectedEdge(x, d);
    	db.addUndirectedEdge(x, e);
    	db.addUndirectedEdge(d, e);
    	
    	return db;
    }

	@Test
	void testNumUndirectedEdges()
	{
		//Given
		SegmentNodeDatabase db = build();
		
		assertEquals(10, db.numUndirectedEdges());
		
		//Manipulating number of undirected edges through removal & clearing.
		PointNode a = new PointNode("A", 3, 6);
		PointNode b = new PointNode("B", 2, 4);
		PointNode c = new PointNode("C", 4, 4);
		db._adjLists.remove(a);
		assertEquals(8, db.numUndirectedEdges());
		db._adjLists.remove(b);
		assertEquals(5, db.numUndirectedEdges());
		db._adjLists.clear();
		assertEquals(0, db.numUndirectedEdges());
		
		//Tests adding a few back, just to be thorough.
		db.addUndirectedEdge(a, b);
		db.addUndirectedEdge(b, c);
		assertEquals(2, db.numUndirectedEdges());
		
	}
	
	@Test
	void testAddDirectedEdges()
	{
		
		SegmentNodeDatabase db = build();
		
		//Adds two directed edges, checking to make sure undirected edge size didn't change
		PointNode p1 = new PointNode("", 0, -0.00001);
    	PointNode p2 = new PointNode("B", 2, 4);
    	PointNode p3 = new PointNode("L", 10.5, 0.123);
    	assertEquals(10, db.numUndirectedEdges());       
    	db.addDirectedEdge(p1, p2);
    	db.addDirectedEdge(p2, p3);
    	assertEquals(10, db.numUndirectedEdges());
    	
    	//Also checks to make sure they were added
    	assertTrue(db._adjLists.get(p1).contains(p2));
    	assertFalse(db._adjLists.get(p1).contains(p3));
    	
    	//Adds the other direction, meaning numUndirected increases
    	assertEquals(10, db.numUndirectedEdges());
    	db.addDirectedEdge(p2, p1);
    	assertEquals(11, db.numUndirectedEdges());
    	
    	//Tried this, but causes errors, due to _point1 from PointNode being null.
    	//db.addDirectedEdge(null, null);
    	//assertEquals(11, db.numUndirectedEdges());
		
	}
	
	@Test
	void testAddUndirectedEdges()
	{
		SegmentNodeDatabase db = build();
		
		PointNode a = new PointNode("A", 3, 6);
    	PointNode b = new PointNode("B", 2, 4);
    	PointNode c = new PointNode("C", 4, 4);
    	PointNode d = new PointNode("D", 0, 0);

    	//Tests 1 duplicate, 1 with the same start and end, and 1 normal edge. Only the last should be added.
		db.addUndirectedEdge(a, b);
		db.addUndirectedEdge(a, a);
		db.addUndirectedEdge(a, d);
		
		assertEquals(11, db.numUndirectedEdges());
		
		//Tests 3 duplicates of reverse order. None should be added.
		db.addUndirectedEdge(b, a);
		db.addUndirectedEdge(c, a);
		db.addUndirectedEdge(c, b);
		
		assertEquals(11, db.numUndirectedEdges());
		
		PointNode pt = new PointNode("", 0.00001, -2.32);
    	PointNode pt2 = new PointNode(-5, 0);
    	
    	db.addUndirectedEdge(pt, pt2);
    	assertEquals(12, db.numUndirectedEdges());
	}
	
	@Test
	void testAddAL()
	{
		
		//Basic test - making sure that regular keys/lists work.
		List<PointNode> values = new ArrayList<>();
		PointNode key = new PointNode("A", 3, 6);
    	PointNode pt1 = new PointNode("B", 2, 4);
    	PointNode pt2 = new PointNode("C", 4, 4);
    	
		values.add(pt1);
		values.add(pt2);
		
		SegmentNodeDatabase db = build();
		db.addAdjacencyList(key, values);
		
		assertTrue(db._adjLists.get(key).contains(pt1) && db._adjLists.get(key).contains(pt2));
		
		//Next level of testing - funky looking inputs and duplicates.
		PointNode key1 = new PointNode("", 0, -0.00001);
    	PointNode pt3 = new PointNode("B", 2, 4);
    	PointNode pt4 = new PointNode("L", 10.5, 0.123);
    	
		values.add(pt3);
		values.add(pt4);
		
		db.addAdjacencyList(key1, values);
		
		assertTrue(db._adjLists.get(key1).contains(pt3) && db._adjLists.get(key1).contains(pt4));
		assertTrue(db._adjLists.get(key1).size() == 3);
		
		values.clear();
		
		//Testing adding an empty list.
		PointNode key2 = new PointNode("X", 15, -0.01);
		
		db.addAdjacencyList(key1, values);
		
		assertEquals(db._adjLists.get(key2), null);
	}
	
	@Test
	void testAsSL()
	{
		
		//Tests initial build
		SegmentNodeDatabase db = build();
		List<SegmentNode> total =  db.asSegmentList();
		assertEquals(20, total.size());
		
		//Adds 3 more undirected edges/6 more segments - basic test
		PointNode f = new PointNode("F", 0.02, -5.0);
    	PointNode g = new PointNode("G", 1, 1);
    	PointNode h = new PointNode("H", 0, 0);
    	db.addUndirectedEdge(f, g);
    	db.addUndirectedEdge(f, h);
    	db.addUndirectedEdge(g, h);
    	
    	List<SegmentNode> total1 =  db.asSegmentList();
    	assertEquals(26, total1.size());
    	
    	//Adds duplicates that don't change the size of the list
    	db.addUndirectedEdge(g, f);
    	db.addUndirectedEdge(h, g);
    	List<SegmentNode> total2 =  db.asSegmentList();
    	assertEquals(26, total2.size());
		
    	//Adds more  directed edges that are unique and will be added
    	PointNode i = new PointNode("I", 95, -32);
    	PointNode j = new PointNode( 0, 0);
    	PointNode k = new PointNode("K", -0, 100);
    	db.addDirectedEdge(i, k);
    	db.addDirectedEdge(k, j);
    	db.addDirectedEdge(j, i);
    	
    	List<SegmentNode> total4 =  db.asSegmentList();
    	assertEquals(29, total4.size());
    	
    	//Adds their opposites as directed edges - should work
    	db.addDirectedEdge(k, i);
    	db.addDirectedEdge(j, k);
    	db.addDirectedEdge(i, j);
    	List<SegmentNode> total3 =  db.asSegmentList();
    	assertEquals(32, total3.size());
	}
	
	@Test
	void testAsUSL()
	{
		//Tests initial build.
		SegmentNodeDatabase db = build();
		List<SegmentNode> total =  db.asUniqueSegmentList();
		assertEquals(10, total.size());
		
		//Adds 3 more normal, unique edges.
		PointNode f = new PointNode("F", 0.02, -5.0);
    	PointNode g = new PointNode( 1, 1);
    	PointNode h = new PointNode("H", 0, 0);
    	db.addUndirectedEdge(f, g);
    	db.addUndirectedEdge(f, h);
    	db.addUndirectedEdge(g, h);
    	
    	List<SegmentNode> total1 =  db.asUniqueSegmentList();
    	assertEquals(13, total1.size());
    	
    	//Adds more directed edges that are unique and will be added
    	PointNode i = new PointNode("I", 95, -32);
    	PointNode j = new PointNode( 0, 0);
    	PointNode k = new PointNode("K", -0, 100);
    	db.addDirectedEdge(i, k);
    	db.addDirectedEdge(k, j);
    	db.addDirectedEdge(j, i);
    	
    	List<SegmentNode> total2 =  db.asUniqueSegmentList();
    	assertEquals(16, total2.size());
    	
    	//Adds their opposites as directed edges - should not be added.
    	db.addDirectedEdge(k, i);
    	db.addDirectedEdge(j, k);
    	db.addDirectedEdge(i, j);
    	List<SegmentNode> total3 =  db.asUniqueSegmentList();
    	assertEquals(16, total3.size());
    	
	}
}