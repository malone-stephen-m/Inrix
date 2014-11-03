/**
 * InrixTripLL holds the data for a single Inrix trip in a linked list data structure. It is a linked list of InrixNode objects;
 * I chose linked list over array because it maybe easier to use graphing algorithms.
 * @author SDM
 * @version 1.0
 */
public class InrixTripLL {
	/** the first node of the trip*/
	InrixNode head;
	/** the last node of the trip*/
	InrixNode tail;
	/** number of nodes in the trip*/
	int size;
	/**
	 * adds a new node to the trip. used by the InrixReader class
	 * @param data; a string array of node data
	 */
	public void add(String[] data) {
		try {
			InrixNode node = new InrixNode(data);
			if (head == null) {
				head = node;
				tail = node;
			} else {
				tail.next = node;
				tail = node;
			}
			size++;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	/**
	 * returns the time between the first and last node in seconds
	 * @return time duration
	 */
	public Double getTripDuration() {
		return (tail.time - head.time) * 24 * 60 * 60;
	}
	/** 
	 * returns the average time delay between node points
	 * @return
	 */
	public Double getAvgTimeDelay() {
		return getTripDuration()/size;
	}
	public InrixTripLL deepCopy() {
		InrixTripLL copy = new InrixTripLL();
		InrixNode n = head;
		while (n !=null) {
			copy.add(n.toString().split(","));
			n = n.next;
		}
		return copy;
	}
	/**
	 * a method to check if the trip passes three basic filters:
	 * 1. the average time delay between nodes is < 20 seconds
	 * 2. the trip has atleast 20 nodes
	 * 3. the speed of the last point is below 10 mph
	 * @return true if passes all filters, otherwise false
	 */
	public boolean passesFilters() {
	boolean timeDelayReq = getAvgTimeDelay() < 20;
	boolean numPointsReq = size > 20;
	boolean tailSpeedReq = tail.acData < 10;
	return timeDelayReq && numPointsReq && tailSpeedReq;
	}
	public String toString(){
		InrixNode n = head;
		StringBuilder sb = new StringBuilder();
		while (n !=null) {
			sb.append(n.toString());
			sb.append("\n");
			n = n.next;
		}
		return sb.toString();
	}
	public void clear() {
		head = null;
		tail = null;
	}
}
