package input.components.point;

import java.util.*;

public class PointNodeDatabase {

	 	protected Set<PointNode> ptDatabase;
	
		public PointNodeDatabase()
		{
		ptDatabase = new LinkedHashSet<PointNode>();
		}
		
		public PointNodeDatabase(Set <PointNode> points) 
		{
		ptDatabase = new LinkedHashSet<PointNode>(points);	
		}
		
		public void put(PointNode point) 
		{
			ptDatabase.add(point);
		}
		
		public boolean contains(PointNode point)
		{
			return ptDatabase.contains(point);
		}
		
		public boolean contains(double x, double y)
		{
			PointNode comparison = new PointNode(x, y);
			return ptDatabase.contains(comparison);
		}
		
		public String getName(PointNode point)
		{
			return point.getName();
		}
		
		public String getName(double x, double y)
		{
			PointNode comparison = new PointNode(x, y);
			for (PointNode point: ptDatabase)
			{
				if (comparison.equals(point))return point.getName();
			}
			return null;
		}
		
		public PointNode getPoint(PointNode point)
		{
			return point;
		}
		
		public PointNode getPoint(double x, double y)
		{
			PointNode comparison = new PointNode(x, y);
			for (PointNode point: ptDatabase)
			{
				if (comparison.equals(point))return point;
			}
			ptDatabase.add(comparison);
			return comparison;
		}
}
