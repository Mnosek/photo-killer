package pl.pk.edu.fmi3.photokiller.models;

import java.io.File;
import java.net.URI;

import javafx.scene.control.CheckBox;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Model for file which is needed in implementation table of files in gui.
 */
public class FileModelForTableView {
	CheckBox fileSelection;
	StringProperty fileName;
	StringProperty filePath;
	StringProperty fileSimilarity;
	
	public FileModelForTableView(String fileName, String filePath){
		this.fileSelection = new CheckBox("dsa");
		this.fileName = new SimpleStringProperty(fileName);
		this.filePath = new SimpleStringProperty(filePath);
		this.fileSimilarity = new SimpleStringProperty("");
	}
	
	public FileModelForTableView(String fileName, String filePath, String fileSimilarity){
		this.fileSelection = new CheckBox("dsa");
		this.fileName = new SimpleStringProperty(fileName);
		this.filePath = new SimpleStringProperty(filePath);
		this.fileSimilarity = new SimpleStringProperty(fileSimilarity);
	}

	public CheckBox getFileSelection() {
		return fileSelection;
	}

	public void Checkbox(Boolean fileSelection) {
		//this.fileSelection.set(fileSelection);
	}

	public String getFileName() {
		return fileName.get();
	}
	
	public String getFileSimilarity(){
		return fileSimilarity.get();
	}

	public void setFileName(String fileName) {
		this.fileName.set(fileName);
	}

	public URI getFilePath() {
		return new File(filePath.get()).toURI();
	}
	
	

	public void setFilePath(String filePath) {
		this.filePath.set(filePath);
	}
	
	
}
