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
	
	public FileModelForTableView(String fileName, String filePath){
		this.fileSelection = new SimpleBooleanProperty(false);
		this.fileName = new SimpleStringProperty(fileName);
		this.filePath = new SimpleStringProperty(filePath);
		this.fileSimilarity = new SimpleStringProperty("");

		
	}
	
	public FileModelForTableView(String fileName, String filePath, String fileSimilarity){
		this.fileSelection = new SimpleBooleanProperty(false);
		this.fileName = new SimpleStringProperty(fileName);
		this.filePath = new SimpleStringProperty(filePath);
		this.fileSimilarity = new SimpleStringProperty(fileSimilarity);
	}

	public Boolean getFileSelection() {
		return fileSelection.get();
	}

	public void setFileSelection(Boolean fileSelection) {
		this.fileSelection.set(fileSelection);
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
