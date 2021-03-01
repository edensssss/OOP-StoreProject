package command;

import java.util.TreeMap;
import java.util.Vector;

import model.Product;

public interface Command {
	
	void selectSortToModel(String type) throws Exception;
	
	void addProductToModel(String name, String catalogNumber, int price, int priceOfSale, String customerName,
			String phoneNumber, boolean notifications) throws Exception;
	
	Product loadProductToModel(String catalogNumber) throws Exception;
	
	Vector<Product> showProductsToModel() throws Exception;
	
	void removeLastProductAdded();
	
	TreeMap<String, Product> showProfitToModel() throws Exception;
	
	String sendMessageToModel(String msg) throws Exception;
	
	
	
}
