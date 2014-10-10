
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
	public double distanceH(InrixNode node2) {
		double lat1,lat2,lon1,lon2,deltaLat,deltaLon,a,c,dkm;
		InrixNode node1 = this;
		dkm = 0;
		lat1 = node1.lat * Math.PI / 180;
		lon1 = node1.lon * Math.PI / 180;
		lat2 = node2.lat * Math.PI / 180;
		lon2 = node2.lon * Math.PI / 180;
		deltaLat=lat2-lat1;
		deltaLon=lon2-lon1;
		a= Math.pow(Math.sin((deltaLat)/2),2) + Math.cos(lat1)*Math.cos(lat2) * Math.pow(Math.sin(deltaLon/2),2);
		c = 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
		dkm += 6371*c;
		return dkm;
	}
	public String toString() {
		return type+","+time+","+lat+","+lon+","+source+","+ID+","+acData;
	}
}
