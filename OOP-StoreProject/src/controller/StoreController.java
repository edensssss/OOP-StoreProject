package controller;

import java.util.Map;
import java.util.TreeMap;

import command.StoreCommand;
import listeners.StoreListener;
import listeners.ViewListener;
import model.Product;
import view.StoreView;

public class StoreController implements StoreListener, ViewListener {

	//private Store store;
	private StoreView view;
	private StoreCommand storeCom;

	public StoreController(StoreCommand storeCommand, StoreView view) {
		this.storeCom = storeCommand;
		this.view = view;
		view.registerListeners(this);
		storeCommand.getStore().registerListeners(this);
	}
	
	@Override
	public void selectionSortToModel(String type) {
		try {
			storeCom.selectSortToModel(type);
		} catch (Exception e) {
			view.getLblException().setText(e.getMessage());
			view.getLblException().setVisible(true);
		}
		
	}

	@Override
	public void addProductToModel(String name, String catalogNumber, int price, int priceOfSale, String customerName,
			String phoneNumber, boolean notifications) {
		try {
			storeCom.addProductToModel(name, catalogNumber, price, priceOfSale, customerName, phoneNumber, notifications);
		} catch (Exception e) {
			view.getLblException().setText(e.getMessage());
			view.getLblException().setVisible(true);
		}
	}
	
	@Override
	public void loadProductToModel(String catalogNumber) {

		Product product = storeCom.getStore().getProduct(catalogNumber);
		fireProductToUI(product);
	}
	
	@Override
	public void showProductsToModel() {
		try {
			fireAllProductsToUI(storeCom.showProductsToModel());
		} catch (Exception e) {
			view.getLblException().setText(e.getMessage());
			view.getLblException().setVisible(true);
		}
	}
	
	@Override
	public void removeLastProductAddedToModel() {
		try {
			storeCom.removeLastProductAdded();
		} catch (Exception e) {
			view.getLblException().setText(e.getMessage());
			view.getLblException().setVisible(true);
		}
	}
	
	@Override
	public void showProfitToModel() {
		try {
			fireProfitToUI(storeCom.showProfitToModel());
		} catch (Exception e) {
			view.getLblException().setText(e.getMessage());
			view.getLblException().setVisible(true);
		}
	}
	
	@Override
	public void sendMessageToModel(String saleMessage) {
		view.getLblException().setVisible(true);
		
		StringBuffer msg = new StringBuffer();
		try {
			msg.append(storeCom.sendMessageToModel(saleMessage));
			if(msg.length() == 0)
				view.getLblException().setText("No one wants to get notifications");
			fireMessagesToUI(msg);
		} catch (Exception e) {
			view.getLblException().setText(e.getMessage());
		}
	}

	
//	public void readProductsFromFileToModel(File binFile)
//	{
//		try {
//			store.readProductsFromFile(binFile);
//		} catch (Exception e) {
//			view.getLblException().setText(e.getMessage());
//			view.getLblException().setVisible(true);
//		}
//	}


	@Override
	public void fireProductToUI(Product product) {
		if(product == null)
		{
			view.setLblException("Product does not exist");
			view.getLblException().setVisible(true);
		}
		
		else {
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
	
	@Override
	public void fireAllProductsToUI(TreeMap<String, Product> products) {
		view.getLblException().setVisible(true);
		
		if(products.size()==0)
			view.setLblException("No products have been sold yet");
			
		else {
			view.setLblException("All products:");
			for (Map.Entry<String, Product> entry : products.entrySet())
				view.setLblException(view.getLblException().getText() + "\n" + entry.getValue().toString()); 
		}
		
	}

	@Override
	public void fireProfitToUI(TreeMap<String, Product> products) {
		int profit = 0;
		int totalProfit = 0;
		view.getLblException().setVisible(true);
		
		if(products.size()==0)
			view.setLblException("No products have been sold yet");
		else {
			view.setLblException("The profit is:");
			for (Map.Entry<String, Product> entry : products.entrySet()) {
				profit = entry.getValue().getPriceOfSale()-entry.getValue().getPrice();
				totalProfit = totalProfit + profit;
				view.setLblException(view.getLblException().getText() + "\n" + entry.getKey() + "\t" + profit + "$");
			}
			view.setLblException(view.getLblException().getText() + "\n\nTotal Profit:" + "\t" + totalProfit + "$");
				
		}
		
	}

	@Override
	public void fireMessagesToUI(StringBuffer messages) {
		view.getLblException().setVisible(true);
		view.getLblException().setText(messages.toString());
		
		String saleMsg = messages.toString();
		String[] allMessages = saleMsg.split("\n");
		int numOfCustormers = allMessages.length;
		view.createLblCustomers(numOfCustormers);
		
		for (int i = 0; i < numOfCustormers; i++) {
			view.createNewMsg(i, allMessages[i]);
			view.getLblCustomers()[i].setVisible(false);
			// need to add "vbox" and add children label[i]
			view.getScrollBar().setContent(view.getLblCustomers()[i]);
		}
	
	}




}
