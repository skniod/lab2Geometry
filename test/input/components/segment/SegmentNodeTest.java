/**
* This class tests the SegmentNode class and its methods.
*
*
* @author Flynn Nisbet & Jalen Livingston
* @date 09-07-2023
*/

package input.components.segment;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import input.components.point.PointNode;
class SegmentNodeTest {
	
	@Test
	void equalsTest() {	
	//Creating two segments that share the same ordered pair
	PointNode a = new PointNode("A", 1.0, 1.0);
	PointNode b = new PointNode("B", 2.0, 2.0);
	SegmentNode line1 = new SegmentNode(a, b);
	SegmentNode line4 = new SegmentNode(b, a);
	
	//Creating a segment that has approximately close ordered pairs
	PointNode c = new PointNode("C", 2.123, 2.123);
	PointNode d = new PointNode("D", 2.1234, 2.1234);
	SegmentNode line2 = new SegmentNode(c, d);
	
	//Creating a segment that is has approximately close, negative ordered pairs
	PointNode e = new PointNode("E", 0, -0.555);
	PointNode f = new PointNode("F", 0, -0.5554);
	SegmentNode line6 = new SegmentNode(e, f);
	
	//Creating a segment that is at one single point on the coordinate plane
	PointNode g = new PointNode("G", 0, 0);
	PointNode h = new PointNode("H", 0, 0);
	SegmentNode line3 = new SegmentNode(g, h);
	
	//Creating a second segment that will eventually be used to compare 'distance' with another segment
	PointNode i = new PointNode("I", 5.0, 5.0);
	PointNode j = new PointNode("J", 6.0, 6.0);
	SegmentNode line5 = new SegmentNode(i, j);
	
	//Testing for if the equals(Object obj) method returns True when comparing the segment to itself
	assertTrue(line1.equals(line1));
	
	//Testing for if the equals(Object obj) method returns False when comparing two different segments
	assertFalse(line2.equals(line6));
	
	//Testing for if the equals(Object obj) method returns True if two segments with different starting points are
	//equal to one another
	assertTrue(line1.equals(line4));
	
	//Testing for if the equals(Object obj) method returns True (or False) if two segments of different ordered pairs
	//but same unit length are the same based on the length of the line
	assertFalse(line1.equals(line5));
	
	//Testing for if the equals(Object obj) method returns False if a single point is a segment and does not
	//equal itself
	assertFalse(line3.equals(line3));
	
	}
}
