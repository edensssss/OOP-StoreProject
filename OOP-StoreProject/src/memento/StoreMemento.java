package memento;

import java.time.LocalDate;
import java.util.TreeMap;

import model.Product;
import model.Store;

public class StoreMemento {

	private TreeMap<String, Product> products = new TreeMap<String, Product>();
	private LocalDate created;
	
	public StoreMemento(Store store) {
		this.products = new TreeMap<String,Product>(store.getProducts());
		this.created = LocalDate.now();
	}

	public TreeMap<String, Product> getProducts() {
		return products;
	}

	
	
}
