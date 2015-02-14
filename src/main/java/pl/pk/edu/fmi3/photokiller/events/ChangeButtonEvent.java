package pl.pk.edu.fmi3.photokiller.events;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import pl.pk.edu.fmi3.photokiller.gui.GuiCreatorInterface;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Change button event handler
 */
public class ChangeButtonEvent implements EventHandler<ActionEvent>{
	/**
	 * Enum with type of pressed button
	 */
	public static enum ChangeName{
		Source("source path"),
		Search("search path");
		
		String actionName = "";
		
		/**
		 * Constructor
		 * @param actionName name of pressed button
		 */
		ChangeName(String actionName){
			this.actionName = actionName;
		}
		
		/**
		 * Returns name of pressed button
		 * @return button's name
		 */
		public String getActionName(){
			return actionName;
		}
	}
	
	ChangeName chanName;
	GuiCreatorInterface guiC;
	AbstractChangeControl absChaCon;
	
	
	/**
	 * Constructor
	 * @param nameChangeControl type of pressed button
	 * @param guiC object of GuiCreator
	 */
	public ChangeButtonEvent(ChangeName nameChangeControl, GuiCreatorInterface guiC){
		chanName = nameChangeControl;
		this.guiC = guiC;
	}
	

	/**
	 * Method invokes when the button is pressed
	 * event action sended from window
	 */
	public void handle(ActionEvent event) {
		File newPath;
		if (ChangeName.Search == chanName){
			absChaCon = new SearchChangeControl();
			newPath = absChaCon.selectDirectory();
			if (newPath != null)
				guiC.setSearchFile(newPath);
		}
		else {
			absChaCon = new SourceChangeControl();
			newPath = absChaCon.selectDirectory();
			if (newPath != null)
				guiC.setSourceFile(newPath);
		}
	}

	
	/**
	 * 
	 * @author Michał Policht - michal85so@gmail.com
	 * Calls directory choose window
	 */
	public abstract class AbstractChangeControl{
		protected String controlName;
		
		/**
		 * Opens directory choose window
		 * @return
		 */
		public File selectDirectory(){
			DirectoryChooser dirChoos = new DirectoryChooser();
			dirChoos.setTitle("select your " + controlName);
			return dirChoos.showDialog(null);
		}
	}
	
	
	/**
	 * 
	 * @author Michał Policht - michal85so@gmail.com
	 * astractChangeControl implementation, fired by source path change button 
	 */
	public class SourceChangeControl extends AbstractChangeControl {
		/**
		 * Constructor
		 */
		public SourceChangeControl() {
			controlName = ChangeName.Source.getActionName();
		}
	}
	
	
	/**
	 * 
	 * @author Michał Policht - michal85so@gmail.com
	 * abstractChangeControl implementation, fired by search path change button
	 */
	public class SearchChangeControl extends AbstractChangeControl{
		/**
		 * Constructor
		 */
		public SearchChangeControl(){
			controlName = ChangeName.Search.getActionName();
		}
	}

}
