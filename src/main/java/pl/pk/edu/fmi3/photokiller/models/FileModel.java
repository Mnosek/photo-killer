package pl.pk.edu.fmi3.photokiller.models;

import java.io.File;

/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Simple File model
 */
public class FileModel {
	private String fileName;
	private String filePath;
	public FileModel(String fileName, String filePath){
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
	
	/**
	 * Returns file name
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
	
	
	/**
	 * Sets file name
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	/**
	 * Returns file path
	 * @return string
	 */
	public String getFilePath() {
		return filePath;
	}
	
	
	/**
	 * Sets file path
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	/**
	 * Removes file from the filesystem
	 * @return boolean if file removed
	 */
	public boolean removeFromFilesystem() {
		boolean bool;
		File file = new File(this.getFilePath().replaceAll("file:", "").replaceAll("%20", " "));		
		return file.delete();
	}
}
