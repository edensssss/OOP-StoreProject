package command;

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
	
	public TreeMap<String, Product> execute(String type) throws Exception {
		TreeMap<String, Product> products = null;
		/*
		 * switch (type) { case "Descending": CompareByCatalogNumDESC compDESC = new
		 * CompareByCatalogNumDESC(); products = new TreeMap<String, Product>(compDESC);
		 * break; case "Insertion": CompareByInsertionOrder compInsertion = new
		 * CompareByInsertionOrder(); products = new TreeMap<String,
		 * Product>(compInsertion); break; default: products = new TreeMap<String,
		 * Product>(); break; }
		 */
		
		return products;
	}
}
