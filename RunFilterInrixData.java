
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class RunFilterInrixData {

	public static void main(String[] args) throws IOException{
		//Starttime
		long startTime = System.currentTimeMillis();
		//Folder of the split inrix csvs
		String foldername = "C:/Users/SDM/Desktop/INRIX/RawData";
		final File folder = new File(foldername);
		InrixReader IR = new InrixReader();
		//CityFinder cityfinder = new CityFinder("C:/Users/SDM/Desktop/INRIX/CityFiles/");
		//BufferedWriter writer;
		//BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/SDM/Desktop/INRIX/CityFiles/DallasAfterFilter.csv")));
		//for each split open it, filter it, and write what is filtered
		for (final File fileEntry : folder.listFiles()) {
			System.out.println("File: " + fileEntry.toString());
			ArrayList<InrixTripLL> trips = IR.readCSV(foldername + "/" + fileEntry.getName());
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/SDM/Desktop/INRIX/CityFiles/" + fileEntry.getName())));
			System.out.println("Total Trips: " + trips.size());
			int numPasses = 0;
			int timeDelayFail = 0;
			int numPointsFail = 0;
			int tailSpeedFail = 0;
			int cityPass = 0;
			int USA = 0;
			for (int i = 1; i < trips.size();) {
				//The sorting criteria
				InrixTripLL t = new InrixTripLL();
				t = trips.get(i);
				boolean timeDelayReq = t.getAvgTimeDelay() < 25;
				boolean numPointsReq = t.size > 10;
				boolean tailSpeedReq = t.tail.acData < 20;
				//Debug
				if (!timeDelayReq) {
					timeDelayFail++;
				}
				if (!numPointsReq) {
					numPointsFail++;
				}
				if (!tailSpeedReq) {
					tailSpeedFail++;
				}
				//END DEBUG
				boolean PassesRequirements = timeDelayReq && numPointsReq && tailSpeedReq;
				if (PassesRequirements) {
					numPasses++;
					//find what city the trip is in
//					writer = cityfinder.findCity(t.tail.lat, t.tail.lon);
//					if (writer != null) {
//						writer.write(t.toString());
//						cityPass++;
//					}
					writer.write(t.toString());
				}
				i++;
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Number of Fails Time Delay Filter: " + timeDelayFail);
			System.out.println("Number of Fails Num Points Filter: " + numPointsFail);
			System.out.println("Number of Fails Tail Speed Filter: " + tailSpeedFail);
			System.out.println("Number of Trips that Pass : " + numPasses);
			System.out.println("Number of Trips that Pass City Filters: " + cityPass);
			System.out.println("Number of USA Trips: " + USA);
			System.out.println("Took "+((endTime - startTime)/1000) + "s"); 
			
			trips.clear();
//			System.out.println(trips.size());
//			BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/SDM/Desktop/INRIX/INRIXENDS/INRIXENDS.csv", true));
//		    for (InrixTripLL t : trips) {
//		    	writer.write(t.tail.toString() + "\n");
//		    }
		    writer.close();
//		    outName = "C:/Users/SDM/Desktop/INRIX/INRIXFILTERED/" + "filteredEnd" + j + ".csv";
//		    writer = new BufferedWriter(new FileWriter(new File(outName)));
//		    for (InrixTripLL t : trips) {
//		    	writer.write(t.toString());
//		    }
//		    j++;
		}
		//cityfinder.closeWriters();
//		writer.close();
	}
}
