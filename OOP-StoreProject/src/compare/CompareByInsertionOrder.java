package compare;

import java.util.Comparator;

import model.Product;

public class CompareByInsertionOrder implements Comparator<Product>{
	
	@Override
	public int compare(Product p1, Product p2) {
		return 1;
	}

}


