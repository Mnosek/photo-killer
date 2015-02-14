package pl.pk.edu.fmi3.photokiller.gui;

import javafx.scene.control.Control;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Control factories abstract class
 */
public abstract class AbstractControlsFactory {
	protected Control control = null;
	
	
	/**
	 * Returns controls
	 * @return Control
	 */
	public Control getControl(){
		return control;
	}
}
