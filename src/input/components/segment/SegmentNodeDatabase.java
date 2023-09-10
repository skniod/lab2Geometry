/**
* This class is the product of the PointNode, PointNodeDatabase, and SegmentNode. 
* It creates a map of PointNodes that represent segments in a geometric figure.
* Methods in the class also create a list of SegmentNodes that are represented in the map.
*
*
* @author Flynn Nisbet & Jalen Livingston
* @date 09-07-2023
*/

package input.components.segment;
import input.components.point.PointNode;
import java.util.*;

public class SegmentNodeDatabase{
	
	protected Map<PointNode, Set<PointNode>> _adjLists;
	
	public SegmentNodeDatabase() {
		_adjLists = new LinkedHashMap<>();
		
	}
	
	public SegmentNodeDatabase(Map <PointNode, Set<PointNode>> m){
		_adjLists = m;

	}
	
	public int numUndirectedEdges() {
		
		List<SegmentNode> _list = new ArrayList<>();
		
		//Iterates through keys, then their values and counts them if their opposites are in the list.
		int count = 0;
		for(PointNode pt: _adjLists.keySet()) {	
			for(PointNode p: _adjLists.get(pt)) {
				
				SegmentNode sn = new SegmentNode(pt, p);
				if(_list.contains(sn))count++;
				_list.add(sn);
				
			}
		}
		return count;
	}
	//Supposed to be private? How to test?
	protected void addDirectedEdge(PointNode pt1, PointNode pt2) {
		
		//Checks for the keys existence, so it doesn't override. 
		if(_adjLists.containsKey(pt1)) _adjLists.get(pt1).add(pt2);
		
		//Creates new Set, so that pt2 can be added to the _adjLists.
		else
		{
			Set<PointNode> val = new HashSet<>();
			val.add(pt2);
			_adjLists.put(pt1, val);
		}
	}
	
	public void addUndirectedEdge(PointNode pt1, PointNode pt2) {
		//Two directed edges, forming an undirected edge.
		addDirectedEdge(pt1, pt2);
		addDirectedEdge(pt2, pt1);
	}
	
	public void addAdjacencyList(PointNode key, List<PointNode> values) {
		
		//Checks for the keys existence, so that it doesn't override existing set values.
		if(_adjLists.containsKey(key))_adjLists.get(key).addAll(values);
		
		//If it doesn't exist, creates a new Set, adds values and key to _adjLists
		else {
			Set<PointNode> val = new HashSet<>();
			val.addAll(values);
			_adjLists.put(key, val);
		}
		
	}
	
	public List<SegmentNode> asSegmentList() {
		
		//Loops through all keys, then their sets, adding them to a list.
		List<SegmentNode> segList = new ArrayList<>();
		for(PointNode pt: _adjLists.keySet()) {
			for(PointNode val: _adjLists.get(pt)) {
				
				SegmentNode n = new SegmentNode(pt, val);
				segList.add(n);
			}
		}
		return segList;
	}
		
	public List<SegmentNode> asUniqueSegmentList() {
		
		List<SegmentNode> segList = new ArrayList<>();
		for(PointNode pt: _adjLists.keySet()) {
			for(PointNode val: _adjLists.get(pt)) {
				
				//Same as asSL, but only adds to the list if its opposite segment, which is the same, is in the list
				SegmentNode n = new SegmentNode(pt, val);
				SegmentNode temp = new SegmentNode(val, pt);
				if(!(segList.contains(temp)))segList.add(n);
			}
		}
		return segList;
	}
}