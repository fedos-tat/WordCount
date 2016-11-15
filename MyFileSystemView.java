
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;

public class MyFileSystemView extends FileSystemView{
	   
	public static ArrayList<File> fileList = new ArrayList<File>();
	  public static CopyOnWriteArrayList<File> ListDirFile(String inputParam)
	  {
	  	  FileSystemView fileSystemView = FileSystemView.getFileSystemView();
	      File currentDirectory = new File(inputParam);

	      File[] filesAndDirs = fileSystemView.getFiles(currentDirectory, false);
	      String filename;
	      String extension; 
	      App.fileList.clear();
	      int count_dir =0;
	     // adding files from first level child directories 
		  for(File ff:filesAndDirs){
			  filename = ff.getName();
			  extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
	          if(!extension.equals("zip")) {
 			    // System.out.println("FilesAndDirs files ="+ ff);
				 if(ff.isDirectory()){
					// System.out.println("dir ="+ ff);
					 File [] files_tmp = ff.listFiles();
					 for(File tmp : files_tmp){
					   if(tmp.isDirectory()) {count_dir ++;	}  //child dir of level>1
					 }
				     Collections.addAll(fileList, files_tmp);
				 }
	          } 
		  }
		  //// adding files from 2...n levels child directories 
		  while(count_dir>0){
			  ArrayList<File> fileListTmp = new ArrayList<File>();
			     for(File ff : fileList){
					filename = ff.getName();
					extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
			        if(!extension.equals("zip")) {
				      if(ff.isDirectory()){
					     File [] files_tmp1 = ff.listFiles();
					     Collections.addAll(fileListTmp, files_tmp1);
					     count_dir --;
					     for(File tmp : files_tmp1){
						   if(tmp.isDirectory()) {count_dir ++;	} 
					     }   
				      } 
			        } else fileListTmp.add(ff);  //add .zip
				 }fileList.addAll(fileListTmp);
		   }	

          fileList.addAll(Arrays.asList(filesAndDirs));
          System.out.println("MyFileSys fileList size ="+fileList.size());
	      for(File file : fileList) {
	    	//  System.out.println(file);
	        if (!file.isDirectory()) {
	        	filename = file.getName();
	        	extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
	        	if(extension.equals("txt")) 
                { App.fileList.add(file);}
	        	
                if(extension.equals("zip")) 
                  { boolean unZipFileResult = UnZipFile.UnZipFile(file);}
	        }    
	      }
	      for(File file: App.fileList){
	      System.out.println("FILE="+ file);}
         //Local fileList
	      fileList.clear();
	      return App.fileList;
}

	@Override
	public File createNewFolder(File arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}}



