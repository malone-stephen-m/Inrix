<<<<<<< HEAD
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class RunFilterInrixData {

//	public static void main(String[] args) throws IOException{
//		// TODO Auto-generated method stub
//		InrixReader IR = new InrixReader();
//		ArrayList<InrixTripLL> trips = IR.readCSV("C:/Users/SDM/Desktop/INRIX/Splits/segment_as.csv");
//		System.out.println(trips.size());
//		for (int i = 0; i < trips.size();) {
//			if (trips.get(i).getAvgTimeDelay() > 13 || trips.get(i).getTripDuration() < 120) {
//				trips.remove(i);
//			} else {
//				i++;
//			}
//		}
//		System.out.println(trips.size());
//		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/SDM/Desktop/INRIX/segment_asfilteredEnds.csv", true));
//	    for (InrixTripLL t : trips) {
//	    	writer.write(t.tail.toString() + "\n");
//	    }
//	    writer.close();
//	    writer = new BufferedWriter(new FileWriter(new File("C:/Users/SDM/Desktop/INRIX/segment_asfilteredtrips.csv")));
//	    for (InrixTripLL t : trips) {
//	    	writer.write(t.toString());
//	    }
//	    writer.close();
//	}
	public static void main(String[] args) throws IOException{
		String foldername = "C:/Users/SDM/Desktop/INRIX/INRIX MOBILE";
		final File folder = new File(foldername);
		InrixReader IR = new InrixReader();
		String outName;
		int j = 1;
		for (final File fileEntry : folder.listFiles()) {
			System.out.println(j);
			ArrayList<InrixTripLL> trips = IR.readCSV(foldername + "/" + fileEntry.getName());
			System.out.println(trips.size());
			for (int i = 0; i < trips.size();) {
				if (trips.get(i).getAvgTimeDelay() > 13 || trips.get(i).getTripDuration() < 120) {
					trips.remove(i);
				} else {
					i++;
				}
			}
			System.out.println(trips.size());
			BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/SDM/Desktop/INRIX/INRIXENDS/INRIXENDS.csv", true));
		    for (InrixTripLL t : trips) {
		    	writer.write(t.tail.toString() + "\n");
		    }
		    writer.close();
		    outName = "C:/Users/SDM/Desktop/INRIX/INRIXFILTERED/" + "filteredEnd" + j + ".csv";
		    writer = new BufferedWriter(new FileWriter(new File(outName)));
		    for (InrixTripLL t : trips) {
		    	writer.write(t.toString());
		    }
		    writer.close();
		    j++;
		}
	}
}
=======
import java.io.File;
import java.io.IOException;


public class RunFilterInrixData {
	
	public static void main(String[] args) throws IOException{
		//File to be analyzed
		String filePath = "/Users/Stephen/Documents/Java/DataManagement/bin/";
		File parentFileName=new File("/Users/Stephen/Documents/Java/DataManagement/bin/SFO_points_Jan2014.csv");
		File outFile = new File(filePath +"SortedAndMerged" + parentFileName.getName());
		InrixReader.mergeSort(filePath,parentFileName, outFile);
	}

}
>>>>>>> origin/master
