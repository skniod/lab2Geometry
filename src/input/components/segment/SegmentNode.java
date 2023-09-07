package input.components.segment;

import input.components.point.PointNode;

/**
 * A utility class only for representing ONE segment
 */
public class SegmentNode
{
	protected PointNode _point1;
	protected PointNode _point2;
	
	public PointNode getPoint1() { return _point1; }
	public PointNode getPoint2() { return _point2; }
	
	public SegmentNode(PointNode pt1, PointNode pt2)
	{
		this._point1 = pt1;
		this._point2 = pt2;
	}

	@Override
	public boolean equals(Object obj)
	{
		//Checking obj, casting as a SegmentNode
		if (obj == null) return false;
		if (!(obj instanceof SegmentNode)) return false;
		SegmentNode o = (SegmentNode) obj;
		
		//Finding which o point that _point1 is equal to, so that we can compare _point2 with the other
		boolean which = true;
		if ((_point1.equals(o.getPoint1())) && !(_point1.equals(o.getPoint2())));
		else if (!(_point1.equals(o.getPoint1())) && (_point1.equals(o.getPoint2()))) which = false;
		else return false;
		
		//Compares _point2 with the remaining point
		if(which) return (_point2.equals(o.getPoint2()));
		return (_point2.equals(o.getPoint1()));
	}
	public String toString()
    {
		return  "(" + this._point1 + ", " + this._point2 + ")";
	}
}