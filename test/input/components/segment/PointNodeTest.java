/**
* This class tests the PointNode class and its methods.
*
*
* @author Flynn Nisbet & Jalen Livingston
* @date 09-07-2023
*/

package input.components.segment;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import input.components.point.PointNode;
class PointNodeTest {
	
	@Test
	void equalsTest() {
		
		//Creating PointNodes (a and b) to represent as basic ordered pairs
		PointNode a = new PointNode("A", 1.0, 1.0);
		PointNode b = new PointNode("B", 2.0, 2.0);
		
		//Creating PointNodes (c and d) to represent as ordered pairs that are approxiamtely close in position
		PointNode c = new PointNode("C", 2.123, 2.123);
		PointNode d = new PointNode("D", 2.1234, 2.1234);
		
		//Creating PointNodes (e and f) to represent as negative ordered pairs that are approxiamately close in position
		PointNode e = new PointNode("E", 0, -0.555);
		PointNode f = new PointNode("F", 0, -0.5554);
		
		//Creating PointNodes (g and h) to represent as two different named points that exist in the same position
		PointNode g = new PointNode("G", 0, 0);
		PointNode h = new PointNode("H", 0, 0);
		
		//Testing if the equals(Object obj) method returns True if a point is equal to itself
		assertTrue(a.equals(a));
		
		//Testing if the equals(Object obj) method returns False if two points do not equal one another
		assertFalse(a.equals(b));
		
		//Testing if the equals(Obejct obj) method returns True if two--approxiamately close--points do not equal
		assertFalse(c.equals(d));
		
		//Testing if the equals(Object obj) method returns False if two--approxiamately close-- negative points do not equal
		assertFalse(e.equals(f));
		
		//Testing if the equals(Object obj) method returns True if two different named points are the same based on position
		assertTrue(g.equals(h));
		
	}
	
}