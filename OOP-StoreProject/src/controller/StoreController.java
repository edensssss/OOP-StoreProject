package controller;

import java.util.ArrayList;

import listeners.StoreListener;
import listeners.ViewListener;
import model.Customer;
import model.Product;
import model.Store;
import view.StoreView;

public class StoreController implements StoreListener, ViewListener {

	private Store store;
	private StoreView view;

	public StoreController(Store store, StoreView view) {
		this.store = store;
		this.view = view;
		view.registerListeners(this);
		store.registerListeners(this);
	}

	@Override
	public void addProductToModel(String name, String catalogNumber, int price, int priceOfSale, String customerName,
			String phoneNumber, boolean notifications) {
		try {
			store.addProductToStore(name, catalogNumber, price, priceOfSale, customerName, phoneNumber, notifications);
		} catch (Exception e) {
			view.getLblException().setText(e.getMessage());
			view.getLblException().setVisible(true);
		}
	}

	@Override
	public void fireCustomersToUI(ArrayList<Customer> customers) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadProductToModel(String catalogNumber) {

		Product product = store.getProduct(catalogNumber);
		fireProductToUI(product);
	}

	@Override
	public void fireProductToUI(Product product) {
		view.setTfProductName(product.getName());

		// product details
		view.setTfProductName(product.getName());
		view.setTfPriceOfProduct(product.getPrice());
		view.setTfPriceOfProductSale(product.getPriceOfSale());

		// customer details
		view.setTfCustomerPhoneNumber(product.getCustomer().getPhoneNumber());
		view.setTfCustomerName(product.getCustomer().getName());
		view.setCheckBox(product.getCustomer().getNotifications());

	}

}
