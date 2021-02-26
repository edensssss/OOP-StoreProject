package compare;

import java.util.Comparator;

import model.Product;

public class CompareByCatalogNumDESC implements Comparator<Product>{
	
	@Override
	public int compare(Product p1, Product p2) {
		return p2.getNumber().compareTo(p1.getNumber());
	}

}
