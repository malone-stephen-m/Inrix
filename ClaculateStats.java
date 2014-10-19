import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ClaculateStats {

	public static void main(String[] args) throws IOException {
		InrixReader IR = new InrixReader();
		//read the good trips
		ArrayList<InrixTripAR> trips = IR.readCSVAR("C:/Users/SDM/Desktop/INRIX/SanFranGoodTrips.csv");
		//write the stats to a file
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/SDM/Desktop/INRIX/SanFranStats.csv")));
		//for each trip write down its stats
		int i = 0;
		for (InrixTripAR t : trips) {
			System.out.println("i:" + i);
			int start = t.breachRadius(.805);
			if (start > 0) {
				System.out.println("start:" + start);
				int end = t.size()-1;
				double dist = t.distanceH(start, end);
				//convert to miles
				dist = dist * .62137;
				System.out.println("dist:" + dist);
				double time = t.timeDifference(start, end);
				//convert to minutes
				time = time * 24 * 60;
				System.out.println("time:" + time);
				//speed in mph
				double avgVel = dist/time*60;
				String ID = t.get(0).ID;
				String s = ID + "," + dist + "," + time + "," + avgVel + "\n";
				writer.write(s);
			}
			i++;
		}
		writer.close();
	}

}
