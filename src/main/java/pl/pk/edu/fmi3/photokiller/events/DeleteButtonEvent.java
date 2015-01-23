package pl.pk.edu.fmi3.photokiller.events;

import java.io.File;
import java.util.ArrayList;

import pl.pk.edu.fmi3.photokiller.gui.GUICreator;
import pl.pk.edu.fmi3.photokiller.messages.Messages;
import pl.pk.edu.fmi3.photokiller.models.FileModel;
import pl.pk.edu.fmi3.photokiller.models.ObserverListModel;
import pl.pk.edu.fmi3.photokiller.models.SearchModel;
import pl.pk.edu.fmi3.photokiller.models.SearchObserverListModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Class which is invoke when the button delete is pressed.
 */
public class DeleteButtonEvent implements EventHandler<ActionEvent>{


	GUICreator guiC;
	private ArrayList<FileModel> selectedList;

	public DeleteButtonEvent(GUICreator guiC) {
		this.guiC = guiC;
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		selectedList = guiC.getDuplicateTable().getSelectedItemsAsArrayList();
		
		
		if (selectedList.isEmpty()) {
			Messages.messagesMe("Wybierz pliki do usunięcia!");
		} else {
			
		}
		
	}
}
