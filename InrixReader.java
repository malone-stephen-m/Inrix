
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.*;
import java.lang.instrument.Instrumentation;
import java.lang.Object;
import java.util.Collection;
import java.util.List;



public class InrixReader {
	public ArrayList<InrixTripLL> readCSV(String fileName) throws FileNotFoundException{
		ArrayList<InrixTripLL> Trips = new ArrayList<InrixTripLL>();
		Scanner scanner = new Scanner(new File(fileName));
	    String line = scanner.nextLine();
	    String nextline;
	    InrixTripLL curTrip = new InrixTripLL();
	    while(scanner.hasNext()){
	    	nextline = scanner.nextLine();
	    	String[] row = line.split(",");
	    	String[] nextrow = nextline.split(",");
	    	curTrip.add(row);
	    	if (!row[5].equals(nextrow[5])) {
	    		Trips.add(curTrip.deepCopy());
	    		curTrip.clear();
	    	}
	    	line = nextline;
	    }
	    scanner.close();
	    return Trips;
	}
	public ArrayList<InrixTripLL> testCSV(String fileName) throws IOException{
		ArrayList<InrixTripLL> Trips = new ArrayList<InrixTripLL>();
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = null;
		Scanner scanner = null;
 
        while ((line = reader.readLine()) != null) {
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String data = scanner.next();
                System.out.print(Float.parseFloat(data) + ",");
            }
            System.out.print("\n");
        }
	    scanner.close();
	    reader.close();
	    return Trips;
	}
	public Hashtable<Double,InrixTripLL> readCSVhashtable(String fileName) throws FileNotFoundException {
		Hashtable<Double,InrixTripLL> trips = new Hashtable<Double,InrixTripLL>();
		Scanner scanner = new Scanner(new File(fileName));
	    String line = scanner.nextLine();
	    String nextline;
	    InrixTripLL curTrip = new InrixTripLL();
	    while(scanner.hasNext()){
	    	nextline = scanner.nextLine();
	    	String[] row = line.split(",");
	    	String[] nextrow = nextline.split(",");
	    	curTrip.add(row);
	    	if (!row[5].equals(nextrow[5])) {
	    		trips.put(curTrip.head.ID,curTrip.deepCopy());
	    		curTrip.clear();
	    	}
	    	line = nextline;
	    }
	    scanner.close();
	    return trips;
	}

	
	public static void mergeSort(String filePath,File parentFile, File outFile) throws FileNotFoundException{
		try{
			List<InrixComp> Trips = new ArrayList<InrixComp>();
			BufferedReader br = new BufferedReader(new FileReader(filePath + parentFile.getName()));
			InrixWriter iw = new InrixWriter();
		    String line = br.readLine();
		    int numSplits = 0;
		    
		    while((line = br.readLine())!=null){
		    	String[] splitz = line.split(",");
		    		if (Trips.size()<100000){
		    			if (splitz.length >6){
		    			InrixComp curLine = new InrixComp(splitz);
				    	Trips.add(curLine);
		    			}
				    }else{
				    	System.out.println(("Printing" + numSplits));
				    	iw.convertAndPrint(Trips, filePath, true, true, numSplits);
						numSplits++;
				    	Trips.clear();
				    }
			    }
			    
			    File[] files = FindCSV(filePath + "/Splits/");
			    br.close();
		        iw.mergeFiles(files, outFile, new compareBothTimeID());
			}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
	public static File[] findCSVFiles(String filePath){
        File[] filesInDirectory = new File(filePath).listFiles();  
        Collection<String> files = new ArrayList<String>();
        for(File f : filesInDirectory){  
            String fp = f.getAbsolutePath();
            String fileExtenstion = fp.substring(fp.lastIndexOf(".") + 1,fp.length()); 
            if("csv".equals(fileExtenstion)){  
                files.add(f.getName());  
            }
        }  
        return files.toArray(new File[]{});
    }  
	public static File[] FindCSV(String filePath){
		File dir = new File(filePath);
		return dir.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String fileName){
				return fileName.endsWith(".csv");}						
			});
	}
	public static double estimateBucketSize(String fileName){
		//Get our constants for analysis
		long sysMemory = getAvailableMemory();
		double bucketsize = getFileSize(fileName)/1024;
		//To not create too many or too few buckets, we need a little trial and error.
		//We do not want to create more than 1024 temporary files on disk. Too Damn Many!
		//If the bucket size is less than half of the available system memory, cool. 
		//Let's grow it to our maximum (3/4 of the systems memory)
		//If there are way too many buckets, we might need to reallocate resources.
		if (bucketsize < sysMemory*.75)
			bucketsize = sysMemory*.75;
		else{
			if(bucketsize >= sysMemory){
				System.out.println("We will probably run out of memory.");
			}
		}
		return bucketsize;
	}
    private static long getFileSize(String filename) {
    	  //Returns file size of filename. If the file does not exist, returns a error.
	      File file = new File(filename);
	      if (!file.exists() || !file.isFile()) {
	         System.out.println("File doesn't exist");
	         return -1;
	      }
	      return file.length();
	}
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
    public static long getAvailableMemory() {
    	  Runtime runtime = Runtime.getRuntime();
    	  long totalMemory = runtime.totalMemory(); // current heap allocated to the VM process
    	  long freeMemory = runtime.freeMemory(); // out of the current heap, how much is free
    	  long maxMemory = runtime.maxMemory(); // Max heap VM can use e.g. Xmx setting
    	  long usedMemory = totalMemory - freeMemory; // how much of the current heap the VM is using
    	  long availableMemory = maxMemory - usedMemory; // available memory i.e. Maximum heap size minus the current amount used
    	  return availableMemory;
    	}


}

