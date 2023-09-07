package input.components.segment;

import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;

public class PointNodeDatabaseTest {
	@Test
	public void build()
    {
		//Creating various PointNodes, including null values, doubles, negatives, etc.
    	PointNode a = new PointNode("A", 3, 6);
    	PointNode b = new PointNode(null, -5.6, -9.0);
    	PointNode c = new PointNode(3, 6);
    	PointNode d = new PointNode(-0.5, 4.809998);
    	PointNode e = new PointNode(" ", -0.5, 4.809999);
    	
    	//Testing PointNode functions, like toString and equals, as well as the construction of nodes themselves. 
    	assertFalse(a.toString().equals(b.toString()));
    	assertTrue(a.toString().equals("A (3.0, 6.0)"));
    	assertFalse(a.toString().equals(c.toString()));
    	assertFalse(d.equals(e));
    	
    	
    }
	@Test
	public void dataTesting() {
		
		PointNode a = new PointNode("A", 3, 6);
    	PointNode b = new PointNode(" ", 3, 6);
    	PointNode c = new PointNode(-0.5, 4.80999);
    	PointNode d = new PointNode(" ", -0.5, 4.80998);
    	
    	PointNodeDatabase ptDatabase = new PointNodeDatabase();
    	ptDatabase.put(a);
    	assertTrue(ptDatabase.contains(a));
    	ptDatabase.put(b);
    	assertTrue(ptDatabase.getName(3, 6).equals("A"));
    	ptDatabase.put(c);
    	assertTrue(ptDatabase.getPoint(c).equals(c));
    	assertTrue(ptDatabase.getPoint(-0.5, 4.80999).equals(c));
    	ptDatabase.put(d);
    	assertTrue(ptDatabase.getName(d).equals(" "));
    	assertTrue(ptDatabase.getName(-0.5, 4.80998).equals(" "));
    	
	}
}
