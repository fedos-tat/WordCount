
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class UnZipFile {

	public static boolean UnZipFile(File file){
	 	 try {

	 		 String strPath = file.getAbsolutePath();

	 		 BufferedOutputStream dest = null;
	         FileInputStream fis = new FileInputStream(file);
	         ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
	         ZipEntry entry;
	         while((entry = zis.getNextEntry()) != null) {
	          //  System.out.println("Extracting: " +entry.getName());
	            int count;
	            File d = new File(entry.getName(), strPath);
	            File unZipFileName = new File(entry.getName());

	            byte data[] = new byte[2048];
	            // write the files to the disk
	            FileOutputStream fos = new  FileOutputStream(entry.getName());
	            dest = new  BufferedOutputStream(fos, 2048);
	            while ((count = zis.read(data, 0, 2048)) 
	              != -1) {
	               dest.write(data, 0, count);
	               //System.out.println(count);
	            }
	            dest.flush();
                // add unzipped file
	            App.fileList.add(unZipFileName);
	            dest.close();
	         }
	         zis.close();
	     } catch(Exception ex) {
	         ex.printStackTrace();
	     }
	 	 //for(File file1: App.fileList){
	 	// System.out.println("App.fileList ="+ file1);}
	 	 return true;
	}
}



