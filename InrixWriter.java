import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InrixWriter {
	public void mergeFiles(File[] inFiles, File outFile, Comparator<InrixComp> comparator) throws IOException{
	      try{
	         BufferedReader[] readers = new BufferedReader[inFiles.length];
	         PrintWriter writer = new PrintWriter(outFile);
	         TreeMap<InrixComp, Integer> treeMap = new TreeMap<InrixComp, Integer>(comparator);

	         for (int i = 0; i < inFiles.length; i++){
	            readers[i] = new BufferedReader(new FileReader(inFiles[i]));
	            String line = readers[i].readLine();
	            if (line!= null){
	            	treeMap.put(new InrixComp(line.split("\t")), Integer.valueOf(i));
	            }  
	         }

	         while (!treeMap.isEmpty()){
	            Map.Entry<InrixComp, Integer> nextToGo = treeMap.pollFirstEntry();
	            int fileIndex = nextToGo.getValue().intValue();
	            writer.print(nextToGo.getKey());

	            String line = readers[fileIndex].readLine();
	            if (line != null)
	               treeMap.put(new InrixComp(line.split("\t")), Integer.valueOf(fileIndex));
	         }
	         writer.close();
	      }
	      catch(Exception ex) {
	    	  ex.printStackTrace();
	      }
	   }
	public void convertAndPrint(List<InrixComp> List, String filePath, boolean writeToFile, 
			boolean sortTheList, int numSplits){
        if (List != null){
            if(sortTheList){
                Collections.sort(List,new compareBothTimeID());
            }
        }     
        if(writeToFile) {
            try{
            	File theDir = new File(filePath + "Splits");
            	if (!theDir.exists()) {
            	    System.out.println("creating directory: " + "Splits");
            	    System.out.println(theDir.getPath().toString());
            	    try{
            	        theDir.mkdir();
            	     } catch(SecurityException se){
            	        se.printStackTrace();
            	     }        
            	  }
                BufferedWriter out = new BufferedWriter(new FileWriter(filePath+"/Splits/chunk_" + numSplits + ".csv", false));
                Iterator<InrixComp> iter = List.iterator();
                StringBuilder builder = new StringBuilder();
                if(iter.hasNext()) {
                	  // first entry with no comma
                	  builder.append(iter.next());
                	}
                	while(iter.hasNext()) {
                	  // subsequent entries with a preceding comma
                	  builder.append(iter.next());
                	}
                out.write(builder.toString());
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
 
    }
}



