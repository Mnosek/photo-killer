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

	private List<File> searchColection = new ArrayList<File>();
	private List<File> sourceColection = new ArrayList<File>();
	
	public SearchModel(File searchPath, File sourcePath) {
		
		SearchPath(searchPath, searchColection);
		SearchPath(sourcePath, sourceColection);
		
	}
	
	private void SearchPath(File path, List<File> result) {
		
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
}
