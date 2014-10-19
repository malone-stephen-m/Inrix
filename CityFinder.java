import java.awt.Polygon;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



/**
 * A class to determine which city a lat,long coordinate is in
 * @author SDM
 *
 */
public class CityFinder {
	ArrayList<City> cities;
	/**
	 * upon instantiation, cities are loaded and csv files for them are formed in
	 * the folder specified
	 * @throws IOException
	 * @param folder name for the folder in which the city files will be stored
	 */
	public CityFinder(String foldername) throws IOException {
		cities = new ArrayList<City>();
		//String foldername = "C:/Users/SDM/Desktop/INRIX/CityFiles/";
		cities.add(new City("Atlanta",3392,-8451,3359,-8420,foldername));
		cities.add(new City("New York",4100,-7421,4052,-7359,foldername));
		cities.add(new City("Los Angeles",3433,-11856,3374,-11763,foldername));
		cities.add(new City("Chicago",4176,-8451,3359,-8420,foldername));
		cities.add(new City("Houston",3392,-8451,3359,-8420,foldername));
		cities.add(new City("Philadelphia",3392,-8451,3359,-8420,foldername));
		cities.add(new City("San Diego",3392,-8451,3359,-8420,foldername));
		cities.add(new City("Dallas",3392,-8451,3359,-8420,foldername));
		cities.add(new City("San Francisco",3820,-12302,3724,-12143,foldername));
		cities.add(new City("Seattle",3392,-8451,3359,-8420,foldername));
		cities.add(new City("Washington",3392,-8451,3359,-8420,foldername));
		cities.add(new City("Boston",3392,-8451,3359,-8420,foldername));
		cities.add(new City("Portland",3392,-8451,3359,-8420,foldername));
		cities.add(new City("Phoenix",3392,-8451,3359,-8420,foldername));
		cities.add(new City("Detroit",3392,-8451,3359,-8420,foldername));
	}
	/**
	 * searches a lat long and finds the accosiated city. returns the buffered writer
	 * to that city's file;
	 * @param lat
	 * @param lon
	 * @return the writer to the city's file
	 */
	public BufferedWriter findCity(double lat, double lon) {
		lat = lat * 100;
		lon = lon * 100;
		if (isUSA(lat,lon)) {
			for (City c : cities) {
				if (c.p.contains(lat,lon)) {
					return c.bw;
				}
			}
		}
		return null;
	}
	/**
	 * A method to close the buffered writers so your computer does not crash and catch fire, which will catch the building on fire,
	 * the heat of the fire explodes a near by transformer, then a near by drug dealer's illegal electronic cash transfer to the drug cartel does'nt
	 * get through in time for the cartel to pay off the cops, which begins a war in whatever obscure South American country they hail from.
	 * The flow of oil from this country is completely cut off, oil prices around the world sky rocket and society is on the verge of collapse.
	 * Nations wage war on each other and soon nukes start flying. Now the world is a desolate radioactive wasteland all because you forgot to close
	 * the buffered writers.
	 * 
	 * @throws IOException
	 */
	public void closeWriters() throws IOException {
		for (City c : cities) {
			c.bw.close();
		}
	}
	/**
	 * inner class that hold specified city data
	 * @author SDM
	 *
	 */
	public class City {
		String name;
		Polygon p;
		BufferedWriter bw;
		public City(String name, int N, int W, int S, int E, String foldername) throws IOException {
			this.name = name;
			int latPoly[] = {N,N,S,S};
			int lonPoly[] = {W,E,E,W};
			this.p = new Polygon(latPoly,lonPoly,4);
			this.bw = new BufferedWriter(new FileWriter(new File(foldername+name+".csv")));
		}
	}
	private boolean isUSA(double lat, double lon) {
		if (lon <-6264 && lon > -12662) {
			if (lat > 2300 && lat < 5000) {
				return true;
			}
		}
		 return false;
	}
}
