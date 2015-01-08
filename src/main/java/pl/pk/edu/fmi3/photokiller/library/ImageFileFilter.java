package pl.pk.edu.fmi3.photokiller.library;

import java.io.File;
import java.io.FileFilter;


/**
 * 
 * @author Wojciech Polus - polusw@hotmail.com
 * Image file filter
 */
public class ImageFileFilter implements FileFilter{
	private String[] extensions = new String[] {"jpg", "png", "gif", "bmp"};
	
	@Override
    public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		}
		
		for (String extension : extensions)
	    {
	      if (file.getName().toLowerCase().endsWith(extension))
	      {
	    	  return !file.isHidden();
	      }
	    }
		
		return false;
		
	}
}
