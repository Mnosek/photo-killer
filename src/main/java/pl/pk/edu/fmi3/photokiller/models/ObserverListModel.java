package pl.pk.edu.fmi3.photokiller.models;

import java.io.File;
import java.util.ArrayList;

import pl.pk.edu.fmi3.photokiller.gui.GUICreator;
import javafx.collections.ListChangeListener;


/**
 * Observer list
 * @author Michał Nosek <mmnosek@gmail.com>
 * @author Wojciech Polus <polusw@hotmail.com>
 *
 */
public class ObserverListModel implements ListChangeListener<FileModelForTableView> {

	private ArrayList<File> searchFiles;
	private GUICreator guiC;
	
	
	/**
	 * Constructor
	 * @param fileList
	 * @param guiC
	 */
	public ObserverListModel(ArrayList<File> fileList, GUICreator guiC) {
		this.searchFiles = fileList;
		this.guiC = guiC;
	}
	
	/**
	 * Fired on change
	 */
	@Override
	public void onChanged(
			javafx.collections.ListChangeListener.Change<? extends FileModelForTableView> file) {
			while (file.next()) {
				if(file.wasAdded()) {
				
				for (FileModelForTableView additem : file.getAddedSubList()) {
					guiC.setSourceImage(additem);
				}
				
				CompareModel compare = new CompareModel(searchFiles, guiC, file);
				compare.start();
				}
			}
	}
	
}
