package pl.pk.edu.fmi3.photokiller.models;

import java.io.File;
import java.net.URI;

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
	BooleanProperty fileSelection;
	StringProperty fileName;
	StringProperty filePath;
	StringProperty fileSimilarity;
	
	public FileModelForTableView(boolean fileSelection, String fileName, String filePath){
		this.fileSelection = new SimpleBooleanProperty(fileSelection);
		this.fileName = new SimpleStringProperty(fileName);
		this.filePath = new SimpleStringProperty(filePath);
		this.fileSimilarity = new SimpleStringProperty("");
	}
	
	public FileModelForTableView(boolean fileSelection, String fileName, String filePath, String fileSimilarity){
		this.fileSelection = new SimpleBooleanProperty(fileSelection);
		this.fileName = new SimpleStringProperty(fileName);
		this.filePath = new SimpleStringProperty(filePath);
		this.fileSimilarity = new SimpleStringProperty(fileSimilarity);
	}

	public BooleanProperty getFileSelectionProperty() {
		return fileSelection;
	}
	
	public Boolean getFileSelection() {
		return fileSelection.getValue();
	}

	public void setFileSelection(Boolean fileSelection) {
		this.fileSelection.set(fileSelection);
	}

	public StringProperty getFileNameProperty(){
		return fileName;
	}
	
	public String getFileName() {
		return fileName.get();
	}

	public void setFileName(String fileName) {
		this.fileName.set(fileName);
	}

	public StringProperty getFilePathProperty(){
		return filePath;
	}
	
	public URI getFilePath() {
		return new File(filePath.get()).toURI();
	}
	
	public void setFilePath(String filePath) {
		this.filePath.set(filePath);
	}
	
	public String getFileSimilarity(){
		return fileSimilarity.get();
	}
		
}
