
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class RunFilterInrixData {

	public static void main(String[] args) throws IOException{
		//Folder of the split inrix csvs
		String foldername = "C:/Users/SDM/Desktop/INRIX/INRIX MOBILE";
		final File folder = new File(foldername);
		InrixReader IR = new InrixReader();
		String outName;
		//for each split open it, filter it, and write what is filtered
		int j = 1;
		for (final File fileEntry : folder.listFiles()) {
			System.out.println(j);
			ArrayList<InrixTripLL> trips = IR.readCSV(foldername + "/" + fileEntry.getName());
			System.out.println(trips.size());
			for (int i = 0; i < trips.size();) {
				//The sorting criteria
				InrixTripLL t = new InrixTripLL();
				t = trips.get(i);
				if (t.getAvgTimeDelay() > 13 || t.size < 20 || t.tail.acData > 10) {
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
