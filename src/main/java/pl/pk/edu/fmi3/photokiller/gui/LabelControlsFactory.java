package pl.pk.edu.fmi3.photokiller.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Labels control factory
 */
public class LabelControlsFactory extends AbstractControlsFactory{
	
	/**
	 * Constructor
	 * @param name label text
	 */
	public LabelControlsFactory(String name){
		control = new Label(name);
		((Label)control).setAlignment(Pos.BASELINE_RIGHT);
	}
}
