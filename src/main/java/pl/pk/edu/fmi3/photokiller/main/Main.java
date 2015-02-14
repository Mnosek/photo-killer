package pl.pk.edu.fmi3.photokiller.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.pk.edu.fmi3.photokiller.gui.GUICreator;
/**
 * 
 * @author Michał Policht <michal85so@gmail.com>
 * @author Michał Nosek <mmnosek@gmail.com>
 * @author Wojciech Polus <polusw@hotmail.com>
 * @author Jakub Bigos
 * @author Anna Szala
 * @author Kamil Madejski 
 * Would you like to find and remove your photos duplicates? PhotoKiller to the rescue!
 * App main class
 */
public class Main extends Application{
	private final String APP_TITLE = "Photo killer";
	private final Integer DEFAULT_SCREEN_SIZE_WIDTH = 800;
	private final Integer DEFAULT_SCREEN_SIZE_HEIGHT = 600;
	
	/**
	 * Start method
	 * @param args arguments from console
	 */
	public static void main (String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(createPrimaryScene());
		primaryStage.show();
	}
	
	/**
	 * Creates main scene for main frame
	 * @return main scene
	 */
	private Scene createPrimaryScene(){
		GUICreator guiCreator = new GUICreator();
		Scene scene = new Scene(guiCreator.getMainPane(), DEFAULT_SCREEN_SIZE_WIDTH, DEFAULT_SCREEN_SIZE_HEIGHT);
		return scene;
	}
}
