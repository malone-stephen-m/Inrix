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
