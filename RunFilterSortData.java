import java.io.File;
import java.io.IOException;


public class RunFilterSortData {
	
	public static void main(String[] args) throws IOException{
		//File to be analyzed
		long startTime = System.currentTimeMillis();
		String filePath = "C:/Users/SDM/Desktop/INRIX/Day1/";
		File parentFileName=new File("C:/Users/SDM/Desktop/INRIX/Day1/9368701f-a737-4380-b2ca-27ccb6ca500d-r-00005");
		File outFile = new File(filePath +"SortedAndMerged" + parentFileName.getName());
		InrixReader.mergeSort(filePath,parentFileName, outFile);
		long endTime = System.currentTimeMillis();
		System.out.println("Took "+((endTime - startTime)/1000) + "s"); 
	}

}
