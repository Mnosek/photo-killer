package pl.pk.edu.fmi3.photokiller.main;

import java.io.File;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.pk.edu.fmi3.photokiller.models.FileModel;
import pl.pk.edu.fmi3.photokiller.models.FileModelForTableView;
import pl.pk.edu.fmi3.photokiller.models.SearchModel;
import junit.framework.*;

public class MainTest extends TestCase {
	
	
	public void testFileModelForTableView() {
		FileModelForTableView file = new FileModelForTableView(true, "name", "test\\");
		
		boolean fileSelection = file.getFileSelection();
		Assert.assertEquals(true, fileSelection);
		
		file.setFileSelection(false);
		boolean fileSelectionSet = file.getFileSelection();
		Assert.assertEquals(false, fileSelectionSet);
		
		String name = file.getFileName();
		Assert.assertEquals("name", name);
		
		file.setFileName("nameSet");
		String nameSet = file.getFileName();
		Assert.assertEquals("nameSet", nameSet);
		
		String path = "test";
		file.setFilePath(path);
		StringProperty filePath = file.getFilePathProperty();
		StringProperty expect = new SimpleStringProperty(path);
		Assert.assertEquals(expect.get(), filePath.get());
	}
	
	public void testFileModel() {

		FileModel file = new FileModel("fileName", "file\\path");
		
		String fileName = file.getFileName();
		Assert.assertEquals("fileName", fileName);
		
		file.setFileName("newFileName");
		String fileNameNew = file.getFileName();
		Assert.assertEquals("newFileName", fileNameNew);
		
	}

	public void testSearchModel() {
		File file = new File("/home/wojtek/Obrazy"); //Set corectly search path
		SearchModel search = new SearchModel(file, file);
		
		Assert.assertEquals(search.getSearchCollection(), search.getSourceCollection());
	}
	
	public void testSearchModel2() {
		File file = new File("/home/wojtek/Obrazy/test"); //file or directory with badly extension
		
		SearchModel search = new SearchModel(file, file);
		
		Assert.assertEquals(search.getSearchCollection().size(), 0);
	}
}