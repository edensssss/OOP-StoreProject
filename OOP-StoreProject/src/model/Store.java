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
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import listeners.StoreListener;
import memento.CareTaker;
import memento.StoreMemento;
import observ.Receiver;
import observ.SaleMessage;
import observ.Sender;
import view.StoreView;

public class Store implements StoreFunc, Serializable, Sender {

	private static Vector<StoreListener> allListeners = new Vector<StoreListener>();
	private TreeMap<String, Product> products = new TreeMap<String, Product>();
	private CareTaker ct = new CareTaker();

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

		ct.save(new StoreMemento(this));
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
	
	public void removeLastProductAdded() {
		load(ct.load());
		saveProductsToFile();
	}
	
	public TreeMap<String, Product> getProducts() {
		return products;
	}
	
	public StoreMemento save() {
		return new StoreMemento(this);
	}
	
	public void load(StoreMemento sm) {
		products = sm.getProducts();
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


	// applying
	public void notifySale() {

		SaleMessage sale = new SaleMessage("sale");

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

	@Override
	public String sendMessage(SaleMessage saleMsg) {
		StringBuffer msg = new StringBuffer();
		for (Map.Entry<String, Product> entry : products.entrySet()) {
			if (entry.getValue().getCustomer().getNotifications())
				msg.append(entry.getValue().getCustomer().receiveMessage(this, saleMsg));
		}
		
		return msg.toString();
	}
	
	public String sendMsgToCustomers(String msg) {
		SaleMessage saleMsg = new SaleMessage(msg);
		return sendMessage(saleMsg);
	}
	
	public void readProductsFromFile(File binFile) {
		{
			int readPointer = 0;
			
			//String find = txtFldFind.getText(), replaceWith = txtFldReplaceWith.getText();
			try (RandomAccessFile raf = new RandomAccessFile(binFile, "r")) {
				byte[] data = new byte[1];
				while (raf.read(data) != -1) {
					String readText = new String(data);
//					if (find.equals(readText)) {
//						byte[] temp = new byte[(int) (raf.length() - readPointer + find.length())];
//						raf.read(temp);
//						raf.setLength(readPointer);
//						raf.write(replaceWith.getBytes());
//						raf.write(temp);
//						isFound = true;
//					} else {
//						raf.seek(readPointer++);
//					}
					raf.seek(readPointer);
				}
				raf.setLength(readPointer);

			} catch (FileNotFoundException e) {
				System.out.println("DeleteStrFromFileMethodException: File Not Found! " + e.getMessage());
			} catch (IOException e) {
				System.out.println("DeleteStrFromFileMethodException: Input Output Exception! " + e.getMessage());
			}

		}

}



}
