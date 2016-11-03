
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;


public class App {
	public static HashMap<String, Integer> map = new HashMap<String, Integer>();
	public static StringBuffer fileContent = new StringBuffer();
	public static CopyOnWriteArrayList<File> fileList = new CopyOnWriteArrayList<File>();
	
	public static void main(String[] args) {
		App.map.clear();
        App.fileContent.trimToSize();
        App.fileList.clear();
        
		String InputParam1;

	      Scanner in = new Scanner(System.in);
	 
	      System.out.println("Enter a string");
	      InputParam1 = in.nextLine();
	      System.out.println("You entered string "+InputParam1);
	      File file = new File(InputParam1);
	      if (file.exists()){
	         fileList = MyFileSystemView.ListDirFile(InputParam1);
	         if(!fileList.isEmpty()){
	             boolean result = ReadFilesWithWordCount.ReadFilesWithWordCount1();
	             if (!result){System.out.println("Errors while word count process!");}
	         }
	         else System.out.println("Can't find TXT files in "+InputParam1 +" path!");
	      } else System.out.println("Path " + InputParam1 + " does not exits!") ;  
	}

}


