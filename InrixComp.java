import java.math.BigDecimal;
import java.util.Comparator;

public class InrixComp{
	
	public int type;
	public String time;
	public float lat;
	public float lon;
	public int source;
	public String ID;
	public int acData;
	
	public InrixComp(String data[]){
		this.type = Integer.parseInt(data[0]);
		this.time = data[1];
		this.lat = Float.parseFloat(data[2]);
		this.lon = Float.parseFloat(data[3]);
		this.source = Integer.parseInt(data[4]);
		String res = new BigDecimal(data[5]).toPlainString();
		this.ID = res;
		this.acData = Integer.parseInt(data[6]);
		
	}
	
	public int getType(){
		return type;
	}
	public String getTime(){
		return time;
	}
	public float getLat(){
		return lat;
	}
	public float getLon(){
		return lon;
	}
	public int getSource(){
		return source;
	}
	public String getID(){
		return ID;
	}
	public int getacData(){
		return acData;
	}
	
	public void setID(String ID){
		this.ID = ID;
	}
	public void setTime(String Time){
		this.time = Time;
	}
	public void setLat(float Lat){
		this.lat = Lat;
	}
	public void setLon(float Lon){
		this.lon = Lon;
	}
	public void setType(int Type){
		this.type = Type;
	}	
	public void setSource(int Source){
		this.source = Source;
	}
	public void setacData(int acData){
		this.acData = acData;
	}
	public String toString() {
		return type+","+time+","+lat+","+lon+","+source+","+ID+","+acData +"\n";
	}
}
class compareID implements Comparator<InrixComp>{

	@Override
	public int compare(InrixComp o1, InrixComp o2) {
		return o1.ID.compareTo(o2.ID);
	}
}
class compareBothTimeID implements Comparator<InrixComp>{
	@Override
	public int compare(InrixComp o1, InrixComp o2) {
	    int result = o1.ID.compareTo(o2.ID);
	    if(result==0) {
	        return o1.time.compareToIgnoreCase(o2.time);
	    }
	    else {
	        return result;
	    }
	}
	
}
