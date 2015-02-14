package pl.pk.edu.fmi3.photokiller.messages;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Application messages class
 */
public class Messages {
	/**
	 * Shows error message
	 * @param message message to show
	 */
	private static void showError(String message){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	/**
	 * Shows question
	 * @param message message to show on question
	 * @return true if user confirm
	 */
	private static Boolean showQuestion(String message){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm");
		alert.setHeaderText(null);
		alert.setContentText(message);
		Optional<ButtonType> showAndWait = alert.showAndWait();
		if (showAndWait.get() == ButtonType.OK)
			return true;
		return false;
	}
	
	
	/**
	 * Shows message when the paths are empty
	 */
	public static void emptyPath(){
		showError("You have to choose your source and search path!");
	}
	
	/**
	 * Method shows message when files was not selected
	 */
	public static void compareWithoutSelection(){
		showError("You didn't select photos to compare!");
	}
	
	
	/**
	 * method shows confirmation about deleting selected files
	 * @return
	 */
	public static Boolean deleteFiles(){
		return showQuestion("Are you sure you want to delete these files?");
	}
	
	
	/**
	 * Method show information about not implemented functionalities
	 */
	public static void notImplemented(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Not implemented yet.");
		alert.setContentText("This functionality is waiting for implementation and will be available as soon as it possible.");
		alert.showAndWait();
	}
	
	
	/**
	 * Shows information message
	 * @param messages
	 */
	public static void messagesMe(String messages) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText(":(");
		alert.setContentText(messages);
		alert.showAndWait();
	}
}
