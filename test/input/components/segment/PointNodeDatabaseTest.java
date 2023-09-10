/**
* This class tests the PointNodeDatabase class and its methods.
*
*
* @author Flynn Nisbet & Jalen Livingston
* @date 09-07-2023
*/

package input.components.segment;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import static org.junit.jupiter.api.Assertions.*;
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
		
		//Creating PointNodes (a and b) where different named points exist at the same point
		PointNode a = new PointNode("A", 3, 6);
    	PointNode b = new PointNode(" ", 3, 6);
    	//Creating PointNode c that is not named, and is a (negative value, specified double) ordered pair
    	PointNode c = new PointNode(-0.5, 0.0001);
    	//Creating PointNode d that is an 'empty' string, and is a (negative value, specified double) ordered pair
    	PointNode d = new PointNode(" ", -0.5, 0.0002);
    	//Creating PointNodes (e and f) where same named points exists at the same point
    	PointNode e = new PointNode("origin", 0, 0);
    	PointNode f = new PointNode("origin", 0, 0);
    	
    	//PointNodes will be stored in the PointNodeDatabase
    	PointNodeDatabase ptDatabase = new PointNodeDatabase();
    	
    	ptDatabase.put(a);
    	//Testing for if the put(PointNode point) method is 'adding' an ordered pair
    	assertTrue(ptDatabase.contains(a));
    	//Testing for if PointNode b [the 'empty' string] does exists since PointNode a exists already
    	ptDatabase.put(b);
    	assertFalse(ptDatabase.getName(3, 6).equals(b.getName()));
    	assertEquals(ptDatabase.getName(10.0921, 100.12), null);
    	
    	
    	ptDatabase.put(c);
    	//Testing for if the getPoint(PointNode point) method returns an ordered pair
    	assertTrue(ptDatabase.getPoint(c).equals(c));
    	//Testing for if the getPoint(double x, double y) method returns an ordered pair
    	assertTrue(ptDatabase.getPoint(-0.5, 0.0001).equals(c));
    	ptDatabase.put(d);
    	//Testing for if the getName(PointNode point) method returns the name of the point
    	assertTrue(ptDatabase.getName(d).equals(" "));
    	//Testing for if the getName(double x, double y) method returns the name of the point
    	assertTrue(ptDatabase.getName(-0.5, 0.0002).equals(" "));
    	//Testing for if the Override equals() method returns an unequal ordered pair
    	assertFalse(ptDatabase.getPoint(c).equals(ptDatabase.getPoint(d)));
    	
    	ptDatabase.put(e);
    	ptDatabase.put(f);
    	//Testing for if PointNodes (e and f) are equal to ordered pair based on position
    	assertTrue(ptDatabase.getPoint(e).equals(ptDatabase.getPoint(f)));
    	assertTrue(ptDatabase.getName(f).equals(ptDatabase.getName(e)));
    	//Testing for if PointNodes (e and f) are not the same point despite sharing the same position
    	assertFalse(e==f);
	}
}
