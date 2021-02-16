package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import listeners.StoreListener;
import view.StoreView;

public class Store implements StoreFunc, Serializable {

	private static Vector<StoreListener> allListeners = new Vector<StoreListener>();
	private TreeMap<String, Product> products = new TreeMap<String, Product>();

	private static final long serialVersionUID = 1L;
	public static final String F_NAME = "products.txt";
	private File objectFile;
	
	
	public Store() {
		super();
		initFile();
	}

	
	// create data structure: TreeMap
	ObjectOutputStream oOut;
	ObjectInputStream oIn;
	private boolean isAppendableObject;


	// init file
	private void initFile() {

		initAppandable();
		initObjectStream();

	}

	// init appandable
	private void initAppandable() {
		objectFile = new File(F_NAME);
		isAppendableObject = objectFile.exists();
	}
	
	// init Object stream
	private void initObjectStream() {
		resetOutputStream();
		resetInputStream();
	}

	// reset OutputStream
	private void resetOutputStream() {
		try {
			if (isAppendableObject) {
				oOut = new ObjectOutputStream(new FileOutputStream(objectFile, isAppendableObject)) {
					@Override
					protected void writeStreamHeader() throws IOException {
						return;
					}
				};
			} else
				oOut = new ObjectOutputStream(new FileOutputStream(objectFile, isAppendableObject));
		} catch (FileNotFoundException e) {
			System.out.println("resetOutputStream method Exception: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("resetOutputStream method Exception: " + e.getMessage());
		}
	}

	public void addProductToStore(String name, String catalogNumber, int price, int priceOfSale, String customerName,
			String phoneNumber, boolean notifications) throws Exception {

		Customer temp = new Customer(customerName, phoneNumber, notifications);
		Product product = new Product(name, catalogNumber, price, priceOfSale, temp);

		products.put(catalogNumber, product);
		saveProductsToFile();
	}
	
	public Product getProduct(String catalogNumber) {

		if (products.containsKey(catalogNumber))
			return products.get(catalogNumber);
		else
			return null;

	}

	// save Object To File
	private boolean saveProductsToFile() {
		try {
			oOut.writeInt(products.size());
			for (Map.Entry<String, Product> entry : products.entrySet()) {
				
				oOut.writeObject(entry.getValue());
			}

			return true;

		} catch (IOException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	// load Product From File
	private void loadProductFromFile() {
		try {
			if (oIn.available() == 0)
				resetInputStream();

			if (oIn.available() != 0) {
				int n = oIn.readInt();
				Product product = (Product) oIn.readObject();
				products.put(product.getNumber(), product);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	// reset InputStream
	private void resetInputStream() {
		try {
			oIn = new ObjectInputStream(new FileInputStream(objectFile));
		} catch (FileNotFoundException e) {
			System.out.println("resetInputStream Method Exception: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("resetInputStream Method Exception: " + e.getMessage());
		}
	}

	public void showProductInfoByNumber(StoreView storeView) {
		// TODO Auto-generated method stub

	}
	
	// applying 
	public void notifySale() {

		Sale sale = new Sale();
		sale.setValue("sale");

		for (Map.Entry<String, Product> entry : products.entrySet()) {
			if (entry.getValue().getCustomer().getNotifications())
				sale.addObserver(entry.getValue().getCustomer());
		}
	}

	/*
	 * public void sort() { Comparator<Product> comparator = new
	 * CompareByCatalogNum();
	 * 
	 * }
	 */
	
	@Override
	public Iterator<Product> iterator() {
		return new MyIterator();
	}

	public class MyIterator implements Iterator<Product> {
		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < products.size();
		}

		@Override
		public Product next() {
			if (hasNext())
				return products.get(index++);
			return null;
		}
	}


	public void registerListeners(StoreListener newListener) {
		allListeners.add(newListener);
	}

}
