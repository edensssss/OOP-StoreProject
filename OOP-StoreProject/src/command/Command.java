package command;

import java.util.TreeMap;

import model.Product;

public interface Command {
	
	TreeMap<String, Product> selectSortToModel(String type) throws Exception;
	
	void addProductToModel(String name, String catalogNumber, int price, int priceOfSale, String customerName,
			String phoneNumber, boolean notifications) throws Exception;
	
	Product loadProductToModel(String catalogNumber) throws Exception;
	
	TreeMap<String, Product> showProductsToModel() throws Exception;
	
	void removeLastProductAdded();
	
	TreeMap<String, Product> showProfitToModel() throws Exception;
	
	String sendMessageToModel(String msg) throws Exception;
	
	
	
}
