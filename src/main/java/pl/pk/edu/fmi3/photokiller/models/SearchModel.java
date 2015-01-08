package pl.pk.edu.fmi3.photokiller.models;

import java.io.File;
import java.util.ArrayList;

import pl.pk.edu.fmi3.photokiller.library.ImageFileFilter;
import pl.pk.edu.fmi3.photokiller.messages.Messages;


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
		if (searchColection == null) {
			Messages.messagesMe("Nie znaleziono plików w ścieżce SearchPath");
		}
		SearchPath(sourcePath, sourceColection);
		if (sourceColection == null) {
			Messages.messagesMe("Nie znaleziono plików w ścieżce SurcePath");
		}
	}
	
	private void SearchPath(File path, ArrayList<File> result) {
		
		File[] collection = path.listFiles(new ImageFileFilter());
		
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
	
	public ArrayList<File> getSearchCollection()
	{
		return searchColection;
	}
}
