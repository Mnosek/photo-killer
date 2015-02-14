 package pl.pk.edu.fmi3.photokiller.models;

import javafx.collections.ListChangeListener;
import pl.pk.edu.fmi3.photokiller.gui.GUICreator;


/**
 * List observer - search table
 * @author Michał Nosek <mmnosek@gmail.com>
 */
public class SearchObserverListModel implements ListChangeListener<FileModelForTableView> {

	private GUICreator guiC;
	
	
	/**
	 * Constructor
	 * @param guiC
	 */
	public SearchObserverListModel(GUICreator guiC) {
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
						additem.setFileSelection(true);
						guiC.setDuplicateImage(additem);
					}
				}
			}
	}
	
}
