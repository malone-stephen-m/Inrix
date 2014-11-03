import java.math.BigDecimal;
import java.util.Comparator;

public class InrixComp{
	
	public String rand1;
	public String badtime;
	public String kph;
	public int type;
	public String time;
	public float lat;
	public float lon;
	public String source;
	public String ID;
	public int acData;
	
	public InrixComp(String data[]){
		this.rand1 = data[0];
		this.badtime = data[1];
		this.time = data[2];
		this.lat = Float.parseFloat(data[3]);
		this.lon = Float.parseFloat(data[4]);
		this.acData = Integer.parseInt(data[5]);
		this.kph = data[6];
		this.type = Integer.parseInt(data[7]);
		String res = new BigDecimal(data[8]).toPlainString();
		this.ID = res;
		this.source = data[9];
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
	public String getSource(){
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
	public void setSource(String Source){
		this.source = Source;
	}
	public void setacData(int acData){
		this.acData = acData;
	}
	public String toString() {
		return rand1+"\t"+badtime+"\t"+time+"\t"+lat+"\t"+lon+"\t"+acData+"\t"+kph +"\t" +type+"\t"+ID+"\t" + source +"\n";
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
