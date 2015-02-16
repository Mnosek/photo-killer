package pl.pk.edu.fmi3.photokiller.gui;

import java.util.ArrayList;

import pl.pk.edu.fmi3.photokiller.models.ObserverListModel;
import pl.pk.edu.fmi3.photokiller.models.FileModel;
import pl.pk.edu.fmi3.photokiller.models.FileModelForTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.File;

/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * TableView controls factory
 */
public class TableViewControlsFactory extends AbstractControlsFactory {
	public FileModelForTableView selected;
	public ObservableList<FileModelForTableView> tableList;
	
	
	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public TableViewControlsFactory(){
		control = new TableView<FileModelForTableView>();
		TableColumn<FileModelForTableView, Boolean> selectionColumn = new TableColumn<FileModelForTableView, Boolean>();
		TableColumn<FileModelForTableView, String> fileNameColumn = new TableColumn<FileModelForTableView, String>("File name");
		TableColumn<FileModelForTableView, String> filePathColumn = new TableColumn<FileModelForTableView, String>("%");
		
		selectionColumn.setCellValueFactory(new PropertyValueFactory<FileModelForTableView, Boolean>("fileSelection"));
		selectionColumn.setEditable(true);
		selectionColumn.setCellFactory(new Callback<TableColumn<FileModelForTableView, Boolean>, TableCell<FileModelForTableView, Boolean>>() {
			public TableCell<FileModelForTableView, Boolean> call(TableColumn<FileModelForTableView, Boolean> p) {
				final TableCell<FileModelForTableView, Boolean> cell = new TableCell<FileModelForTableView, Boolean>() {
					@Override
					public void updateItem(final Boolean item, boolean empty) {
						if (item == null)
							return;
						super.updateItem(item, empty);
						if (!isEmpty()) {
							final FileModelForTableView fm = getTableView().getItems().get(getIndex());
							CheckBox checkBox = new CheckBox();
							checkBox.selectedProperty().bindBidirectional(fm.getFileSelectionProperty());
							setGraphic(checkBox);
						}
					}
				};
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		fileNameColumn.setCellValueFactory(new PropertyValueFactory<FileModelForTableView, String>("fileName"));
		fileNameColumn.setPrefWidth(300);
		filePathColumn.setCellValueFactory(new PropertyValueFactory<FileModelForTableView, String>("fileSimilarity"));
		filePathColumn.setPrefWidth(50);
		
		if (control instanceof TableView<?>){
			((TableView<FileModelForTableView>)control).getColumns().addAll(selectionColumn,fileNameColumn,filePathColumn);
			((TableView<FileModelForTableView>)control).setEditable(true);
		}
	}
	
	
	/**
	 * Adds items to TableView
	 * @param items ArrayList of items
	 */
	@SuppressWarnings("unchecked")
	public void addItemsToTable(ArrayList<File> items){
		tableList = FXCollections.observableArrayList();
		for (File fm : items)
			tableList.add(new FileModelForTableView(false,fm.getName(), fm.getPath()));
		if (control instanceof TableView<?>)
			((TableView<FileModelForTableView>)control).setItems(tableList);
	}
	
	
	/**
	 * Adds item to table
	 * @param file
	 */
	public void addItemToTable(FileModelForTableView file)
	{
		tableList = ((TableView<FileModelForTableView>)control).getItems();
		tableList.add(file);
		
		if (control instanceof TableView<?>)
			((TableView<FileModelForTableView>)control).setItems(tableList);
	}
	
	
	/**
	 * Removes all table items
	 */
	public void removeItems()
	{
		((TableView<FileModelForTableView>)control).getItems().clear();
	}
	
	
	/**
	 * Removes item from table list
	 * @param FileModel item
	 */
	public void removeItem(FileModel item) {
		tableList = ((TableView<FileModelForTableView>)control).getItems();
		
		for (FileModelForTableView fm : tableList) {
			if (fm.getFilePath().toString().trim().equals(item.getFilePath())) {
				tableList.remove(fm);
			}
		}
	}
	
	
	/**
	 * Returns selected items
	 * @return ArrayList with selected items
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<FileModel> getSelectedItemsAsArrayList(){
		ArrayList<FileModel> selectedItems = new ArrayList<FileModel>();
		
			ObservableList<FileModelForTableView> items = tableList;
			for (FileModelForTableView fm : items) {
				if (fm.getFileSelection())
					selectedItems.add(new FileModel(fm.getFileName(), fm.getFilePath().toString()));
			}
		return selectedItems;
	}
}
