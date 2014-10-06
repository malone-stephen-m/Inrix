import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Scanner;


public class FilterFromOSMProgram {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InrixReader IR = new InrixReader();
		String foldername = "C:/Users/SDM/Desktop/INRIX/INRIXFILTERED";
		final File folder = new File(foldername);
		Hashtable<Double,InrixTripLL> goodtrips = new Hashtable<Double,InrixTripLL>();
		for (final File fileEntry : folder.listFiles()) {
			Hashtable<Double,InrixTripLL> trips = IR.readCSVhashtable(foldername + "/" + fileEntry.getName());
			Scanner scanner = new Scanner(new File("C:/Users/SDM/Desktop/INRIX/INRIX OSM Filter/INRIXOSMENDS.csv"));
		    String line;
		    System.out.println(trips.size());
		    while(scanner.hasNext()){
		    	line = scanner.nextLine();
		    	String[] data = line.split(",");
		    	Double ID = Double.parseDouble(data[0]);
		    	if (data[1].equals("1") && trips.containsKey(ID)) {
			    	goodtrips.put(ID,trips.get(ID));	
		    	}
		    }
		    System.out.println(goodtrips.size());
		    scanner.close();  
		}
		Collection<InrixTripLL> tripsArr = goodtrips.values();
	    BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/SDM/Desktop/INRIX/SanFranfilteredFINAL.csv")));
	    for (InrixTripLL t : tripsArr) {
	    	writer.write(t.toString());
	    }
	    writer.close();
	}

}
