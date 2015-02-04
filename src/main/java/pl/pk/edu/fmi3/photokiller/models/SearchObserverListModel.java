 package pl.pk.edu.fmi3.photokiller.models;

import javafx.collections.ListChangeListener;
import pl.pk.edu.fmi3.photokiller.gui.GUICreator;


/**
 * List observer - search table
 * @author Kamil Madejski
 *
 */
public class SearchObserverListModel implements ListChangeListener<FileModelForTableView> {

	private GUICreator guiC;
	
	public SearchObserverListModel(GUICreator guiC) {
		this.guiC = guiC;
	}
	
	
	@Override
	public void onChanged(
			javafx.collections.ListChangeListener.Change<? extends FileModelForTableView> file) {
			while (file.next()) {
				if(file.wasAdded()) {
					for (FileModelForTableView additem : file.getAddedSubList()) {
						
						additem.setFileSelection(true);
						
						guiC.setDuplicateImage(additem);
					}
				}
			}
	}
	
}
