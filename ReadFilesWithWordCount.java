import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class ReadFilesWithWordCount {
	
     public static boolean  ReadFilesWithWordCount1(){
    	 boolean result = false;
    	 for(File ff: App.fileList){
    		// System.out.println("Global files ="+ ff);
 		   String strPath1 = ff.getAbsolutePath();
		   //path1 for BufferReader
		   Path path1 = Paths.get(strPath1);
		   Charset charset = Charset.forName("US-ASCII");
           try (BufferedReader reader = Files.newBufferedReader(path1, charset)) {
             String line = null;
             while ((line = reader.readLine()) != null) {
                String[] words = line.replaceAll("[^a-zA-Z ]", "").split("\\s+");
                App.fileContent.append(line).append("\n");
                for(String word : words){
                  if (App.map.containsKey(word)) App.map.put(word, App.map.get(word)+1);
                  else App.map.put(word, 1);
                }	  
             }
             reader.close();

           } catch (IOException x) {
              System.err.format("IOException: %s%n", x);
           }
		}
         // Sort keys
    	 TreeMap<String, Integer> sortedByKeyMap= new TreeMap<String, Integer>(App.map);
     	 // store keys(words) separated by ";" to show in "Sorted By Value of occurence in TXT files"
    	 StringBuffer[] sb = new StringBuffer[App.map.size()];
    	 //initialize array
    	 for(int i = 0; i<App.map.size(); i++)
    		 sb[i]= new StringBuffer();
         //Alphabetically sorted list of words
    	 for(Map.Entry<String,Integer> entry : sortedByKeyMap.entrySet())
         { System.out.print(entry.getKey()+ "->"+entry.getValue()+ " ");
            //store semicolon separated words 
            sb[entry.getValue()].append(entry.getKey()).append(";");
            //print histogram for word-occurrences 
            for(int i =0; i <entry.getValue(); i++) {  System.out.print("*");}
            //next line
            System.out.print("\n");
         }
         System.out.print("*********** Sorted By Value of occurence in TXT files *************"+ "\n");
         for(int i = 0; i< sb.length; i ++){
        	 if(sb[i].length() >2 ){
        	    for(int j = 0; j<i; j++){
        	        // print histogram 
        	    	System.out.print("*"); }
        	        // print number of occurrences -> list of words
        	        System.out.println("  "+ i + "-> "+sb[i]); }
         }	
         result = true;
         // clear data structures in case of for(;;) application
         /*if(App.map.size()>0)  App.map.clear();
         if(App.fileContent.length()>0 )    App.fileContent.trimToSize();
         if (App.fileList.size()>0)    App.fileList.clear();
          */
    	 return result; 
     }
}

