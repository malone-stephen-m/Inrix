
public class InrixNode {
	public int type;
	public double time;
	public double lat;
	public double lon;
	public int source;
	public double ID;
	int acData;
	public InrixNode next;
	public InrixNode previous;
	public InrixNode(String[] data) {
		this.type = Integer.parseInt(data[0]);
		this.time = Double.parseDouble(data[1]);
		this.lat = Double.parseDouble(data[2]);
		this.lon =  Double.parseDouble(data[3]);
		this.source = Integer.parseInt(data[4]);
		this.ID =  Double.parseDouble(data[5]);
		this.acData = Integer.parseInt(data[6]);
	}
	public String toString() {
		return type+","+time+","+lat+","+lon+","+source+","+ID+","+acData;
	}
}
