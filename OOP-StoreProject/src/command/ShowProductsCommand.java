package command;

import java.util.TreeMap;
import java.util.Vector;

import model.Product;
import model.Store;

public class ShowProductsCommand {
	private Store store;
	
	public ShowProductsCommand(Store store) {
		this.store = store;
	}
	
//	public TreeMap<String, Product> execute() throws Exception {
//		return store.getProducts();
//	}
	
	public Vector<Product> execute() throws Exception {
		return store.getProductsToVector();
	}
}
