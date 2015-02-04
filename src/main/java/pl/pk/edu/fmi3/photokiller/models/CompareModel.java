package pl.pk.edu.fmi3.photokiller.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import pl.pk.edu.fmi3.photokiller.gui.GUICreator;


/**
 * Images compare model
 * @author Michał Nosek <mmnosek@gmail.com>
 * @author Wojciech Polus <polusw@hotmail.com>
 *
 */
public class CompareModel implements Runnable {
	private Thread t;
	private ArrayList<File> searchFiles;
	private GUICreator guiC;
	private javafx.collections.ListChangeListener.Change<? extends FileModelForTableView> file;
	
	public CompareModel(ArrayList<File> fileList, GUICreator guiC, 
			javafx.collections.ListChangeListener.Change<? extends FileModelForTableView> file) {
		this.searchFiles = fileList;
		this.guiC = guiC;
		this.file = file;
	}
	
	
	
	public void run() {
		file.next();
		for (FileModelForTableView additem : file.getAddedSubList()) {
			this.compareToSearch(additem);
		}
	}
	
	
	
	public void start() {
		t = new Thread (this);
        t.start ();
	}
	
	
	
	public void compareToSearch(FileModelForTableView source)
	{
	    BufferedImage sourceImage = null;
	    BufferedImage targetImage= null;
	    Double similarity;

	    this.guiC.getDuplicateTable().removeItems();
	    try {
	    	sourceImage = ImageIO.read(source.getFilePath().toURL());
	    	Integer i = 0;
	    	
		    for (File fm : this.searchFiles){
		    	targetImage = ImageIO.read(fm.toURI().toURL());
		    	similarity = this.percentageSimilarity(sourceImage, targetImage);
		    	if (similarity > 0) {
		    		this.guiC.fillDuplicateTableList(new FileModelForTableView(false,fm.getName(), fm.getPath(), similarity.toString()));
		    	}
		    	i++;
		    	
		    	
		    	this.guiC.comparsionProgress.setProgress((double)i/this.searchFiles.size());
		    	
			}
	    } catch (IOException e) {
	    	System.out.println(e.getMessage());
	    }
	}


	private Double percentageSimilarity(BufferedImage sourceImage, BufferedImage targetImage) {
		int sourceWidth  = sourceImage.getWidth(null);
	    int targetWidth  = targetImage.getWidth(null);
	    int sourceHeight = sourceImage.getHeight(null);
	    int targetHeight = targetImage.getHeight(null);
	    
	    if ((sourceWidth != targetWidth) || (sourceHeight != targetHeight)) {
	      return (double)0;
	    }
	    
	    long diff = 0;
	    for (int y = 0; y < sourceHeight; y++) {
	    	for (int x = 0; x < sourceWidth; x++) {
		        int rgb1 = sourceImage.getRGB(x, y);
		        int rgb2 = targetImage.getRGB(x, y);
		        int r1 = (rgb1 >> 16) & 0xff;
		        int g1 = (rgb1 >>  8) & 0xff;
		        int b1 = (rgb1      ) & 0xff;
		        int r2 = (rgb2 >> 16) & 0xff;
		        int g2 = (rgb2 >>  8) & 0xff;
		        int b2 = (rgb2      ) & 0xff;
		        diff += Math.abs(r1 - r2);
		        diff += Math.abs(g1 - g2);
		        diff += Math.abs(b1 - b2);
	    	}
	    }
	    double n = sourceWidth * sourceHeight * 3;
	    double p = diff / n / 255.0;
	    return 100 - (p * 100.0);
	}
	
}
