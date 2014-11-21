import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ClaculateStats {

	public static void main(String[] args) throws IOException {
		InrixReader IR = new InrixReader();
		//read the good trips
		ArrayList<InrixTripAR> trips = IR.readCSVAR("C:/Users/SDM/Desktop/INRIX/CityFiles/SortedAndMergedSanJose_Automotive_2013.csv");
		//write the stats to a file
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/SDM/Desktop/INRIX/CityStats/SanJoseStats.csv")));
		//for each trip write down its stats
		for (InrixTripAR t : trips) {
			double lat = t.get(t.size()-1).lat;
			double lon = t.get(t.size()-1).lon;
			//System.out.println("i:" + i);
			//System.out.println("size: " + t.size());
			int end = t.removeSandwiches();
			//System.out.println("end:" + end);
			if (end > 0) {
				//.25 miles
				int start = t.breachRadius(.402,end);
				if (start < end + 1 && start > 0) {
					//System.out.println("start:" + start);
					double dist = t.distanceH(start, end);
					//account for node slightly further than radius
					double acfdist = t.distanceACF(start,end);
					double dx = acfdist - .403;
					dist = dist - dx;
					System.out.println("dx: " + dx);
					//convert to miles
					dist = dist * .62137;
					//System.out.println("dist:" + dist);
					double time = t.timeDifference(start, end);
					//convert to minutes
					time = time * 24 * 60;
					//acount for node slightly outside radius
					double vel = t.avgSpeed(start,start+1);
					double dt = ((dx)/vel)*60;
					System.out.println("dt: " + dt);
					time = time - dt;
					//System.out.println("time:" + time);
					//speed in mph
					double avgVel = dist/time*60;
					String ID = t.get(0).ID;
					String s = ID + "," + lat + "," + lon + "," + dist + "," + time + "," + avgVel;
					writer.write(s);
				
					//.5 miles
					start = t.breachRadius(.805,end);
					if (start < end + 1 && start > 0) {
						//System.out.println("start:" + start);
						dist = t.distanceH(start, end);
						//account for node slightly further than radius
						acfdist = t.distanceACF(start,end);
						dist = dist - (acfdist - .805);
						//convert to miles
						dist = dist * .62137;
						//System.out.println("dist:" + dist);
						time = t.timeDifference(start, end);
						//convert to minutes
						time = time * 24 * 60;
						//acount for node slightly outside radius
						vel = t.avgSpeed(start,start+1);
						dt = ((dx* .62137)/vel)*60;
						System.out.println("dt: " + dt);
						time = time - dt;
						//System.out.println("time:" + time);
						//speed in mph
						avgVel = dist/time*60;
						s ="," + dist + "," + time + "," + avgVel + "\n";
						writer.write(s);
					} else {
						writer.write("\n");
					}
				}
			}
		}
		writer.close();
	}

}
