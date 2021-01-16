package ID_315685461_Eden_Schwartz_StoreProject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javafx.scene.paint.Color;

public class Store {

	// create data structure: Map
	private ArrayList<Product> productsList;
	private Map<String, String> productsMap = new HashMap<>();// key = productNumber , value = productName
	private Comparator<Product> comparator;

	public Store() {
		super();
		productsList = new ArrayList<Product>();
		productsMap = new HashMap<String, String>();
		// comparator = new ProductComparator();
	}

	public void addProduct(ViewModel viewModel, ObjectOutputStream output, ObjectInputStream input) throws ClassNotFoundException, IOException {

		// get inputs from text fields
		int price = 0;
		int priceOfSale = 0;
		String name = viewModel.getCustomerName().getText();
		String phoneNumber = viewModel.getCustomerPhoneNumber().getText();

		String productName = viewModel.getTfProductName().getText();
		String productNumber = viewModel.getTfProductNumber().getText();

		if (viewModel.getTfPriceOfProduct().getText().length() == 0) {
			price = 0;
		} else {
			price = Integer.parseInt(viewModel.getTfPriceOfProduct().getText());
		}
		if (viewModel.getTfPriceOfProductSale().getText().length() == 0) {
			priceOfSale = 0;
		} else {
			priceOfSale = Integer.parseInt(viewModel.getTfPriceOfProductSale().getText());
		}

		productName += " ";
		productNumber += " ";
		name += " ";
		phoneNumber += " ";

		Customer customer = new Customer(name, phoneNumber);
		Product newProduct = new Product(productName, productNumber, price, priceOfSale, customer);


		// System.out.println(newProduct);

		productsList.add(newProduct);
		productsMap.put(phoneNumber, productName);
	//	productsMap
		output.writeObject(productsMap);
		
		// show the update file on screen
		Object o = input.readObject();
		viewModel.status.setFill(Color.GREEN);
		viewModel.status.setText(" Product added succesfully!");
		
		
		System.out.println("print from file: " + o.toString());
		System.out.println("print from array: " + productsList.toString());
		System.out.println("print from productsMap: " + productsMap.toString());
	}



	public void showProductInfoByNumber(ViewModel viewModel, ObjectOutputStream output, ObjectInputStream input) {
		// TODO Auto-generated method stub
		
	}

	public void showAllProducts(ViewModel viewModel, ObjectOutputStream output, ObjectInputStream input) {
	  
		// TODO Auto-generated method stub
	}



}
