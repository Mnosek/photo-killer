package pl.pk.edu.fmi3.photokiller.main;

import pl.pk.edu.fmi3.photokiller.main.lib.FileManager;
import java.io.File;
import java.util.Scanner;

public class Main {
	public static void main (String[] args)
	{
		String nam;
		Scanner odczyt = new Scanner(System.in);
		System.out.println("Ścieżka przeszukiwań:");
		nam = odczyt.nextLine();
	    File aFile = new File(nam);
	    FileManager.Process(aFile);
	}
}
