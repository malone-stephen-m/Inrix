import java.util.ArrayList;


/** represents an single trip of InrixNodes in array format
 * @author SDM
 *
 */
public class InrixTripAR {
	// Backing Array list of Inrix nodes 
	private ArrayList<InrixNode> arr = new ArrayList<InrixNode>();
	/**
	 * adds a new node to the trip. used by the InrixReader class
	 * @param data; a string array of node data
	 */
	public void add(String[] data) {
		try {
			InrixNode node = new InrixNode(data);
			arr.add(node);
		} catch (Exception e) {
			
		}
	}
	/**
	 * 
	 * @param i index
	 * @return node at that location in array
	 */
	public InrixNode get(int i) {
		return arr.get(i);
	}
	public void remove(int i) {
		arr.remove(i);
	}
	/**
	 * Accumulates distance traveled between two nodes in a trip 
	 * using Haversine distance; 
	 * More accurate than distanceP() but slower
	 * @param i index of first node
	 * @param j index of last node
	 * @return double distance traveled between nodes
	 */
	public double distanceH(int i, int j) {
		double lat1,lat2,lon1,lon2,deltaLat,deltaLon,a,c,dkm;
		InrixNode node1, node2;
		dkm = 0;
		node2 = arr.get(i+1);
		while (i < j) {
			node1 = node2;
			node2 = arr.get(i+1);
			lat1 = node1.lat * Math.PI / 180;
			lon1 = node1.lon * Math.PI / 180;
			lat2 = node2.lat * Math.PI / 180;
			lon2 = node2.lat * Math.PI / 180;
			deltaLat=lat2-lat1;
			deltaLon=lon2-lon1;
			a= Math.pow(Math.sin((deltaLat)/2),2) + Math.cos(lat1)*Math.cos(lat2) * Math.pow(Math.sin(deltaLon/2),2);
			c = 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
			dkm += 6371*c;
		}
		return dkm;
	}
	/**
	 * Accumulates distance traveled between two nodes in a trip 
	 * using Pythagorean distance; 
	 * Less accurate than distanceH() but faster
	 * @param i index of first node
	 * @param j index of last node
	 * @return double distance traveled between nodes
	 */
	public double distanceP(int i, int j) {
		double lat1,lat2,lon1,lon2,deltaLat,deltaLon,x,y,dkm;
		InrixNode node1, node2;
		dkm = 0;
		node2 = arr.get(i+1);
		while (i < j) {
			node1 = node2;
			node2 = arr.get(i+1);
			lat1 = node1.lat * Math.PI / 180;
			lon1 = node1.lon * Math.PI / 180;
			lat2 = node2.lat * Math.PI / 180;
			lon2 = node2.lat * Math.PI / 180;
			deltaLat=lat2-lat1;
			deltaLon=lon2-lon1;
			x = deltaLon*Math.cos((lat1+lat2)/2);
			y = deltaLat;
			dkm += 6371*Math.sqrt(x*x + y*y);
		}
		return dkm;
	}
	/**
	 * returns the time between the first and last node in seconds
	 * @return time duration
	 */
	public Double getTripDuration() {
		return (arr.get(arr.size()).time - arr.get(1).time) * 24 * 60 * 60;
	}
	/** 
	 * returns the average time delay between node points
	 * @return
	 */
	public Double getAvgTimeDelay() {
		return this.getTripDuration()/arr.size();
	}
	/**
	 * returns a deep copy of the trip array
	 * @return
	 */
	public InrixTripAR deepCopy() {
		InrixTripAR copy = new InrixTripAR();
		int i = 0;
		while (i < arr.size()) {
			copy.add(arr.get(i).toString().split(","));
			i++;
		}
		return copy;
	}
	/**
	 * returns the Trip in csv format
	 * return String
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (InrixNode n : arr) {
			sb.append(n.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	/**
	 * clear the Array from memory
	 */
	public void clear() {
		arr.clear();
	}
}
