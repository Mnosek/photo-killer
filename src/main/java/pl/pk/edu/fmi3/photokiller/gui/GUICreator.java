package pl.pk.edu.fmi3.photokiller.gui;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import pl.pk.edu.fmi3.photokiller.events.ChangeButtonEvent;
import pl.pk.edu.fmi3.photokiller.events.CompareButtonEvent;
import pl.pk.edu.fmi3.photokiller.events.DeleteButtonEvent;
import pl.pk.edu.fmi3.photokiller.events.SearchButtonEvent;
import pl.pk.edu.fmi3.photokiller.events.ChangeButtonEvent.ChangeName;
import pl.pk.edu.fmi3.photokiller.models.ObserverListModel;
import pl.pk.edu.fmi3.photokiller.models.FileModelForTableView;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * @author Michał Nosek <mmnosek@gmail.com>
 * 
 * Class of main frame of application
 */
public class GUICreator implements GuiCreatorInterface{
	private BorderPane mainPane = null;
	private final String URI_TO_DELETE_ICON = "Resource/file_document_paper_red_g10010.png";
	private final String URI_TO_SEARCH_ICON = "Resource/Search2.png";
	private final String URI_TO_EXIT_ICON = "Resource/file_document_paper_red_g12932.png";
	private final String URI_TO_COMPARE_ICON = "Resource/WorkGroup.png";
	private File sourcePath;
	private File searchPath;
	
	private TableViewControlsFactory sourceFileTable;
	private TableViewControlsFactory duplicateFileTable;
	private ImageView sourceImageView;
	private ImageView duplicateImageView; 
	public ProgressBar comparsionProgress;
	
	
	/**
	 * Contstructor
	 */
	public GUICreator(){
		this.mainPane = new BorderPane();
		createMainWindow();
	}
	
	
	/**
	 * Method add controls to main frame
	 */
	private void createMainWindow(){
		addControlsToNorthSide();
		addControlsToSouthSide();
		addControlsInside();
	}
	
	
	/**
	 * Method adds controls in the top part of frame
	 */
	private void addControlsToNorthSide(){
		FlowPane northPane = new FlowPane();
		AbstractControlsFactory buttonSearch = new ButtonControlsFactory("Search",URI_TO_SEARCH_ICON);
		((Button)buttonSearch.getControl()).setOnAction(new SearchButtonEvent(this));
		AbstractControlsFactory buttonCompare = new ButtonControlsFactory("Compare",URI_TO_COMPARE_ICON);
		((Button)buttonCompare.getControl()).setOnAction(new CompareButtonEvent());
		AbstractControlsFactory buttonExit = new ButtonControlsFactory("Exit",URI_TO_EXIT_ICON);
		((Button)buttonExit.getControl()).setOnAction(event -> System.exit(0));
		AbstractControlsFactory buttonDelete = new ButtonControlsFactory("Delete",URI_TO_DELETE_ICON);
		((Button)buttonDelete.getControl()).setOnAction(new DeleteButtonEvent());
		northPane.getChildren().addAll(buttonExit.getControl(),buttonSearch.getControl(),buttonCompare.getControl(),buttonDelete.getControl());
		mainPane.setTop(northPane);
	}
	
	
	/**
	 * Method adds controls in the bottom part of frame
	 */
	private void addControlsToSouthSide(){
		GridPane southPane = new GridPane();
		
		AbstractControlsFactory labForSource = new LabelControlsFactory("Source path:");
		AbstractControlsFactory tfForSource = new TextfieldControlsFactory(false);
		AbstractControlsFactory butForChangeSource = new ButtonControlsFactory("Change");
		AbstractControlsFactory labForSearch = new LabelControlsFactory("Search path:");
		AbstractControlsFactory tfForSearch = new TextfieldControlsFactory(false);
		AbstractControlsFactory butForChangeSearch = new ButtonControlsFactory("Change");
		
		GridPane.setConstraints(labForSource.getControl(), 1, 1);
		GridPane.setConstraints(tfForSource.getControl(), 2, 1);
		GridPane.setConstraints(butForChangeSource.getControl(), 3, 1);
		GridPane.setConstraints(labForSearch.getControl(), 1, 2);
		GridPane.setConstraints(tfForSearch.getControl(), 2, 2);
		GridPane.setConstraints(butForChangeSearch.getControl(), 3, 2);
		
		((Button)butForChangeSearch.getControl()).setOnAction(new ChangeButtonEvent(ChangeName.Search, this));
		((Button)butForChangeSource.getControl()).setOnAction(new ChangeButtonEvent(ChangeName.Source, this));
		
		ArrayList<Node> controls = new ArrayList<>();
		controls.add(labForSource.getControl());
		controls.add(tfForSource.getControl());
		controls.add(labForSearch.getControl());
		controls.add(tfForSearch.getControl());
		controls.add(butForChangeSource.getControl());
		controls.add(butForChangeSearch.getControl());
		
		southPane.getChildren().addAll(controls);
		mainPane.setBottom(southPane);
	}
	
	
	/**
	 * Method adds controls in the center of main frame
	 */
	private void addControlsInside(){
		GridPane centerPane = new GridPane();
		
		sourceFileTable = new TableViewControlsFactory();
		((TableView<FileModelForTableView>)this.sourceFileTable.getControl()).getColumns().get(2).setVisible(false);
		((TableView<FileModelForTableView>)this.sourceFileTable.getControl()).getColumns().get(0).setVisible(false);
		
		comparsionProgress = new ProgressBar(0);

		duplicateFileTable = new TableViewControlsFactory();
		sourceImageView = new ImageView();
		duplicateImageView = new ImageView();
		
		GridPane.setConstraints(sourceFileTable.getControl(), 1, 1);
		GridPane.setConstraints(duplicateFileTable.getControl(), 2, 1);
		GridPane.setConstraints(sourceImageView, 1, 2);
		GridPane.setConstraints(duplicateImageView, 2, 2);
		GridPane.setConstraints(comparsionProgress, 1, 3);

		
		ArrayList<Node> controls = new ArrayList<>();
		controls.add(sourceFileTable.getControl());
		controls.add(duplicateFileTable.getControl());
		controls.add(sourceImageView);
		controls.add(duplicateImageView);
		controls.add(comparsionProgress);
		
		centerPane.getChildren().addAll(controls);
		mainPane.setCenter(centerPane);
	}
	
	
	/**
	 * Fills source file list
	 * @param ArrayList<File> sourceList
	 */
	public void fillSourceFileList(ArrayList<File> sourceList)
	{
		sourceFileTable.addItemsToTable(sourceList);
	}
	
	
	public void fillDuplicateTableList(FileModelForTableView file)
	{
		duplicateFileTable.addItemToTable(file);
	}
	
	
	public void addSourceTableObserver(ObserverListModel comparator)
	{
		((TableView<FileModelForTableView>)this.sourceFileTable.getControl()).getSelectionModel().getSelectedItems().addListener(comparator);

	}
	
	
	public void setSourceImage(FileModelForTableView tableFile)
	{	
		Image image = new Image(tableFile.getFilePath().toString(), 150, 150, false, false);
		sourceImageView.setImage(image);
	}
	
	
	/**
	 * Method returns main panel
	 * @return main panel
	 */
	public Pane getMainPane(){
		return mainPane;
	}
	
	
	public TableViewControlsFactory getDuplicateTable()
	{
		return this.duplicateFileTable;
	}
	
	/**
	 * Method sets search path in control
	 */
	public void setSearchFile(File searchFile){
		searchPath = searchFile;
		TextField searchTF = ((TextField)((GridPane)mainPane.getChildren().get(1)).getChildren().get(3));
		searchTF.setText(searchPath.getPath());
	}
	
	
	/**
	 * Method sets source path in control
	 */
	public void setSourceFile(File sourceFile){
		sourcePath = sourceFile;
		TextField sourceTF = ((TextField)((GridPane)mainPane.getChildren().get(1)).getChildren().get(1));
		sourceTF.setText(sourcePath.getPath());
	}
	
	
	public File getSearchFile() {
		return searchPath;
	}
	
	public File getSourceFile() {
		return sourcePath;
	}
}
