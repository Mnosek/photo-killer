package pl.pk.edu.fmi3.photokiller.events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pl.pk.edu.fmi3.photokiller.gui.GUICreator;
import pl.pk.edu.fmi3.photokiller.messages.Messages;
import pl.pk.edu.fmi3.photokiller.models.SearchModel;

import java.io.File;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Class which is invoke when the search button is pressed.
 */
public class SearchButtonEvent implements EventHandler<ActionEvent>{
	
	GUICreator guiC;
	private File searchPath;
	private File sourcePath;
	
	public SearchButtonEvent(GUICreator guiC) {
		this.guiC = guiC;
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		searchPath = guiC.getSearchFile();
		sourcePath = guiC.getSourceFile();
		
		if (searchPath == null || sourcePath == null) {
			Messages.messagesMe("Uzupełnij sciezki");
		} else {
			SearchModel search = new SearchModel(searchPath, sourcePath);
			
			Messages.messagesMe("Uzupełnij sciezki");
		}
		
	}

}
