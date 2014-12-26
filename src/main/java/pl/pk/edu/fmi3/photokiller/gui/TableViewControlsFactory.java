package pl.pk.edu.fmi3.photokiller.gui;

import java.util.ArrayList;

import pl.pk.edu.fmi3.photokiller.models.FileModel;
import pl.pk.edu.fmi3.photokiller.models.FileModelForTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Factory of tableview controls
 */
public class TableViewControlsFactory extends AbstractControlsFactory{
	
	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public TableViewControlsFactory(){
		control = new TableView<FileModelForTableView>();
		TableColumn<FileModelForTableView, Boolean> selectionColumn = new TableColumn<FileModelForTableView, Boolean>();
		TableColumn<FileModelForTableView, String> fileNameColumn = new TableColumn<FileModelForTableView, String>("File name");
		TableColumn<FileModelForTableView, String> filePathColumn = new TableColumn<FileModelForTableView, String>("File path");
		if (control instanceof TableView<?>){
			((TableView<FileModelForTableView>)control).getColumns().addAll(selectionColumn,fileNameColumn,filePathColumn);
			((TableView<FileModelForTableView>)control).setEditable(true);
		}
		selectionColumn.setCellValueFactory(new PropertyValueFactory<FileModelForTableView, Boolean>("fileSelection"));
		selectionColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectionColumn));
		selectionColumn.setEditable(true);
		fileNameColumn.setCellValueFactory(new PropertyValueFactory<FileModelForTableView, String>("fileName"));
		fileNameColumn.setPrefWidth(300);
		filePathColumn.setCellValueFactory(new PropertyValueFactory<FileModelForTableView, String>("filePath"));
		filePathColumn.setPrefWidth(550);
	}
	
	/**
	 * Method adds items to tableview
	 * @param items arraylist of items
	 */
	@SuppressWarnings("unchecked")
	public void addItemsToTable(ArrayList<FileModel> items){
		ObservableList<FileModelForTableView> tableList = FXCollections.observableArrayList();
		for (FileModel fm : items)
			tableList.add(new FileModelForTableView(fm.getFileName(), fm.getFilePath()));
		if (control instanceof TableView<?>)
			((TableView<FileModelForTableView>)control).setItems(tableList);
	}
	
	/**
	 * Method returns selected items
	 * @return arraylist with selected items
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<FileModel> getSelectedItemsAsArrayList(){
		ArrayList<FileModel> selectedItems = new ArrayList<FileModel>();
		if (control != null && control instanceof TableView<?>){
			ObservableList<FileModelForTableView> items = ((TableView<FileModelForTableView>)control).getItems();
			for (FileModelForTableView fm : items){
				if (fm.getFileSelection())
					selectedItems.add(new FileModel(fm.getFileName(), fm.getFilePath()));
			}
		}
		return selectedItems;
	}
}
