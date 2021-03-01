package compare;

import java.util.Comparator;

import model.Product;

public class CompareByCatalogNumDESC implements Comparator<String>{
	
	@Override
	public int compare(String s1, String s2) {
		return s2.compareTo(s1);
	}

}
