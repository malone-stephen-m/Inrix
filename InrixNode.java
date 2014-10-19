
/**
 * TODO add deepcopy method;
 * @author SDM
 *
 */

public class InrixNode {
	public int type;
	public double time;
	public double lat;
	public double lon;
	public String source;
	public String ID;
	int acData;
	public InrixNode next;
	public InrixNode previous;
	public InrixNode(String[] data) {
		this.type = Integer.parseInt(data[7]);
		this.time = datenum(data[2]);
		this.lat = Double.parseDouble(data[3]);
		this.lon =  Double.parseDouble(data[4]);
		this.source = data[9];
		this.ID =  data[8];
		this.acData = Integer.parseInt(data[5]);
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
	public double datenum(String date) {
		//01234567890123456789
		//2014-01-24 16:12:35.000
		Double day = Double.parseDouble(date.substring(8, 10));
		Double hour = Double.parseDouble(date.substring(11, 13));
		Double min = Double.parseDouble(date.substring(14, 16));
		Double sec = Double.parseDouble(date.substring(17, 19));
		return day + hour/24 + min/24/60 + sec/24/60/60;
	}
}
