package command;

import java.util.Map;
import java.util.TreeMap;

import compare.CompareByCatalogNumDESC;
import compare.CompareByInsertionOrder;
import javafx.scene.control.RadioButton;
import model.Product;
import model.Store;

public class SortSelectionCommand {
	private Store store;

	public SortSelectionCommand(Store store) {
		this.store = store;
	}

	public void execute(String type) throws Exception {
		store.createSortedProducts(type);
	}
}
