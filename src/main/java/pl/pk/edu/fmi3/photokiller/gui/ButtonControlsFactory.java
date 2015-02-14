package pl.pk.edu.fmi3.photokiller.gui;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Button controls factory
 */
public class ButtonControlsFactory extends AbstractControlsFactory{
	
	/**
	 * Constructor
	 * @param name button name
	 */
	public ButtonControlsFactory(String name){
		control = new Button(name);
	}
	
	
	/**
	 * Constructor
	 * @param name button name
	 * @param pathToImage image path
	 */
	public ButtonControlsFactory(String name, String pathToImage){
		Image im = new Image(new File(pathToImage).toURI().toString(), 40, 40, false, false);
		control = new Button(name,new ImageView(im));
		((Button)control).setPrefWidth(150);
	}
}
