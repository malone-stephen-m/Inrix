
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
		String foldername = "C:/Users/SDM/Desktop/INRIX/Day1";
		final File folder = new File(foldername);
		InrixReader IR = new InrixReader();
		CityFinder cityfinder = new CityFinder("C:/Users/SDM/Desktop/INRIX/CityFiles/");
		BufferedWriter writer;
		//for each split open it, filter it, and write what is filtered
		int j = 1;
		for (final File fileEntry : folder.listFiles()) {
			System.out.println("File: " + fileEntry.toString());
			ArrayList<InrixTripLL> trips = IR.readCSV(foldername + "/" + fileEntry.getName());
			System.out.println("Total Trips: " + trips.size());
			int numPasses = 0;
			int timeDelayFail = 0;
			int numPointsFail = 0;
			int tailSpeedFail = 0;
			int cityPass = 0;
			for (int i = 1; i < trips.size();) {
				//The sorting criteria
				InrixTripLL t = new InrixTripLL();
				t = trips.get(i);
				boolean timeDelayReq = t.getAvgTimeDelay() < 13;
				boolean numPointsReq = t.size > 20;
				boolean tailSpeedReq = t.tail.acData < 10;
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
					writer = cityfinder.findCity(t.tail.lat, t.tail.lon);
					if (writer != null) {
						System.out.println("size: " + t.size);
						writer.write(t.toString());
						cityPass++;
					}
				}
				i++;
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Number of Fails Time Delay Filter: " + timeDelayFail);
			System.out.println("Number of Fails Num Points Filter: " + numPointsFail);
			System.out.println("Number of Fails Tail Speed Filter: " + tailSpeedFail);
			System.out.println("Number of Trips that Pass : " + numPasses);
			System.out.println("Number of Trips that Pass City Filters: " + cityPass);
			System.out.println("Took "+((endTime - startTime)/1000) + "s"); 
			cityfinder.closeWriters();
			trips.clear();
//			System.out.println(trips.size());
//			BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/SDM/Desktop/INRIX/INRIXENDS/INRIXENDS.csv", true));
//		    for (InrixTripLL t : trips) {
//		    	writer.write(t.tail.toString() + "\n");
//		    }
//		    writer.close();
//		    outName = "C:/Users/SDM/Desktop/INRIX/INRIXFILTERED/" + "filteredEnd" + j + ".csv";
//		    writer = new BufferedWriter(new FileWriter(new File(outName)));
//		    for (InrixTripLL t : trips) {
//		    	writer.write(t.toString());
//		    }
//		    writer.close();
//		    j++;
		}
	}
}
