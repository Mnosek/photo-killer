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
import pl.pk.edu.fmi3.photokiller.events.DeleteButtonEvent;
import pl.pk.edu.fmi3.photokiller.events.SearchButtonEvent;
import pl.pk.edu.fmi3.photokiller.events.ChangeButtonEvent.ChangeName;
import pl.pk.edu.fmi3.photokiller.models.ObserverListModel;
import pl.pk.edu.fmi3.photokiller.models.FileModelForTableView;
import pl.pk.edu.fmi3.photokiller.models.SearchObserverListModel;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * @author Michał Nosek <mmnosek@gmail.com>
 * 
 * Application main frame class
 */
public class GUICreator implements GuiCreatorInterface{
	private BorderPane mainPane = null;
	private final String URI_TO_DELETE_ICON = "Resource/file_document_paper_red_g10010.png";
	private final String URI_TO_SEARCH_ICON = "Resource/Search2.png";
	private final String URI_TO_EXIT_ICON = "Resource/file_document_paper_red_g12932.png";
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
	 * Adds controls to main frame
	 */
	private void createMainWindow(){
		addControlsToNorthSide();
		addControlsToSouthSide();
		addControlsInside();
	}
	
	
	/**
	 * Adds controls in the top part of frame
	 */
	private void addControlsToNorthSide(){
		FlowPane northPane = new FlowPane();
		AbstractControlsFactory buttonSearch = new ButtonControlsFactory("Search",URI_TO_SEARCH_ICON);
		((Button)buttonSearch.getControl()).setOnAction(new SearchButtonEvent(this));
		AbstractControlsFactory buttonExit = new ButtonControlsFactory("Exit",URI_TO_EXIT_ICON);
		((Button)buttonExit.getControl()).setOnAction(event -> System.exit(0));
		AbstractControlsFactory buttonDelete = new ButtonControlsFactory("Delete",URI_TO_DELETE_ICON);
		((Button)buttonDelete.getControl()).setOnAction(new DeleteButtonEvent(this));
		northPane.getChildren().addAll(buttonExit.getControl(),buttonSearch.getControl(),buttonDelete.getControl());
		mainPane.setTop(northPane);
	}
	
	
	/**
	 * Adds controls in the bottom part of frame
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
	 * Adds controls in the center of main frame
	 */
	private void addControlsInside(){
		GridPane centerPane = new GridPane();
		Pane bottomPane = new Pane();
		
		sourceFileTable = new TableViewControlsFactory();
		((TableView<FileModelForTableView>)this.sourceFileTable.getControl()).getColumns().get(2).setVisible(false);
		((TableView<FileModelForTableView>)this.sourceFileTable.getControl()).getColumns().get(0).setVisible(false);
		
		comparsionProgress = new ProgressBar(0);
		comparsionProgress.setPrefWidth(700);

		duplicateFileTable = new TableViewControlsFactory();
		sourceImageView = new ImageView();
		duplicateImageView = new ImageView();
		
		GridPane.setConstraints(sourceFileTable.getControl(), 1, 1);
		GridPane.setConstraints(duplicateFileTable.getControl(), 2, 1);
		GridPane.setConstraints(sourceImageView, 1, 3);
		GridPane.setConstraints(duplicateImageView, 2, 3);
		GridPane.setConstraints(comparsionProgress, 1, 2, 3, 1);

		
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
	
	
	/**
	 * Fills duplicate file list
	 * @param file
	 */
	public void fillDuplicateTableList(FileModelForTableView file)
	{
		duplicateFileTable.addItemToTable(file);
	}
	
	
	/**
	 * Attaches source table observer
	 * @param comparator
	 */
	public void addSourceTableObserver(ObserverListModel comparator)
	{
		((TableView<FileModelForTableView>)this.sourceFileTable.getControl()).getSelectionModel().getSelectedItems().addListener(comparator);

	}
	
	
	/**
	 * Attaches search table observer
	 * @param observator
	 */
	public void addSearchTableObserver(SearchObserverListModel observator) {
		((TableView<FileModelForTableView>)this.duplicateFileTable.getControl()).getSelectionModel().getSelectedItems().addListener(observator);
	}
	
	
	/**
	 * Sets source image preview
	 * @param tableFile selected table row
	 */
	public void setSourceImage(FileModelForTableView tableFile)
	{	
		Image image = new Image(tableFile.getFilePath().toString());
		sourceImageView.setPreserveRatio(true);
		sourceImageView.setFitWidth(200);
		sourceImageView.setImage(image);
	}
	
	
	/**
	 * Sets duplicate image preview
	 * @param tableFile
	 */
	public void setDuplicateImage(FileModelForTableView tableFile)
	{
		Image image = new Image(tableFile.getFilePath().toString());
		duplicateImageView.setPreserveRatio(true);
		duplicateImageView.setFitWidth(200);
		duplicateImageView.setImage(image);
	}
	
	
	/**
	 * Returns main panel
	 * @return main panel
	 */
	public Pane getMainPane(){
		return mainPane;
	}
	
	
	/**
	 * Returns table of possible duplicates
	 * @return TableViewControlsFactory
	 */
	public TableViewControlsFactory getDuplicateTable()
	{
		return this.duplicateFileTable;
	}
	
	
	/**
	 * Sets search path in control
	 */
	public void setSearchFile(File searchFile){
		searchPath = searchFile;
		TextField searchTF = ((TextField)((GridPane)mainPane.getChildren().get(1)).getChildren().get(3));
		searchTF.setText(searchPath.getPath());
	}
	
	
	/**
	 * Sets source path in control
	 */
	public void setSourceFile(File sourceFile){
		sourcePath = sourceFile;
		TextField sourceTF = ((TextField)((GridPane)mainPane.getChildren().get(1)).getChildren().get(1));
		sourceTF.setText(sourcePath.getPath());
	}
	
	
	/**
	 * Returns search path
	 * @return string
	 */
	public File getSearchFile() {
		return searchPath;
	}
	
	
	/**
	 * Returns source path
	 * @return string
	 */
	public File getSourceFile() {
		return sourcePath;
	}
}
