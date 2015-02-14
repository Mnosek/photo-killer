package pl.pk.edu.fmi3.photokiller.events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pl.pk.edu.fmi3.photokiller.gui.GUICreator;
import pl.pk.edu.fmi3.photokiller.messages.Messages;
import pl.pk.edu.fmi3.photokiller.models.ObserverListModel;
import pl.pk.edu.fmi3.photokiller.models.SearchModel;
import pl.pk.edu.fmi3.photokiller.models.SearchObserverListModel;

import java.io.File;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Search button event handler
 */
public class SearchButtonEvent implements EventHandler<ActionEvent>{
	
	GUICreator guiC;
	private File searchPath;
	private File sourcePath;
	
	
	/**
	 * Constructor
	 * @param guiC gui creator
	 */
	public SearchButtonEvent(GUICreator guiC) {
		this.guiC = guiC;
	}
	

	/**
	 * Event handler
	 * @param ActionEvent
	 */
	@Override
	public void handle(ActionEvent event) {
		
		searchPath = guiC.getSearchFile();
		sourcePath = guiC.getSourceFile();
		
		if (searchPath == null || sourcePath == null) {
			Messages.messagesMe("Uzupełnij sciezki");
		} else {
			SearchModel search = new SearchModel(searchPath, sourcePath);
			guiC.fillSourceFileList(search.getSourceCollection());
			guiC.addSourceTableObserver(new ObserverListModel(search.getSearchCollection(), guiC));
			guiC.addSearchTableObserver(new SearchObserverListModel(guiC));
		}	
	}
}
