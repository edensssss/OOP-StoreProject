package model;

import java.util.Comparator;

public class CompareByCatalogNum implements Comparator<Product>{
	
	@Override
	public int compare(Product p1, Product p2) {
		return p1.getNumber().compareTo(p2.getNumber());
	}

}


