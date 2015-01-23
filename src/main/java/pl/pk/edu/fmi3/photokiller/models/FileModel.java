package pl.pk.edu.fmi3.photokiller.models;

import java.io.File;

/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Model for file
 */
public class FileModel {
	private String fileName;
	private String filePath;
	public FileModel(String fileName, String filePath){
		this.fileName = fileName;
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public void removeFromFilesystem() {
		File file = new File(this.getFilePath());
		file.delete();
	}
}
