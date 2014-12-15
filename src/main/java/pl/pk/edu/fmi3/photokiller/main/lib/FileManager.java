package pl.pk.edu.fmi3.photokiller.main.lib;

import java.io.File;

public class FileManager {

	static int spc_count=-1;

	  public static void Process(File aFile) {
	    spc_count++;
	    String spcs = "";
	    for (int i = 0; i < spc_count; i++)
	      spcs += " ";
	    if(aFile.isFile())
	      System.out.println(spcs + "[FILE] " + aFile.getName());
	    else if (aFile.isDirectory()) {
	      System.out.println(spcs + "[DIR] " + aFile.getName());
	      File[] listOfFiles = aFile.listFiles();
	      if(listOfFiles!=null) {
	        for (int i = 0; i < listOfFiles.length; i++)
	          Process(listOfFiles[i]);
	      } else {
	        System.out.println(spcs + " [ACCESS DENIED]");
	      }
	    }
	    spc_count--;
	  }
	
}
