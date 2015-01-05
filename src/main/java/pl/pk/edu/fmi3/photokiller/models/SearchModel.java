package pl.pk.edu.fmi3.photokiller.models;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author Wojciech Polus - polusw@hotmail.com
 * Search Model
 */
public class SearchModel {

	private ArrayList<File> searchColection = new ArrayList<File>();
	private ArrayList<File> sourceColection = new ArrayList<File>();
	
	public SearchModel(File searchPath, File sourcePath) {
		
		SearchPath(searchPath, searchColection);
		SearchPath(sourcePath, sourceColection);
				
	}
	
	private void SearchPath(File path, ArrayList<File> result) {
		
		File[] collection = path.listFiles(new FileFilter() {
		    @Override
		    public boolean accept(File file) {
		        return !file.isHidden();
		    }
		});
		
		for (File file : collection) {
			if (file.isDirectory()) {
				SearchPath(file, result);
			} else {
				result.add(file);
			}
		}	
	}
	
	public ArrayList<File> getSourceCollection()
	{
		return sourceColection;
	}
}
