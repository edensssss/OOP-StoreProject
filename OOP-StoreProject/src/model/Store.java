package model;

import java.io.File;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import compare.CompareByCatalogNumDESC;
import compare.CompareByInsertionOrder;
import iterator.FileIterator;
import listeners.StoreListener;
import memento.CareTaker;
import memento.StoreMemento;
import observ.SaleMessage;
import observ.Sender;

public class Store implements Serializable, Sender {

	private static Vector<StoreListener> allListeners = new Vector<StoreListener>();
	private TreeMap<String, Product> products;
	private CareTaker ct = new CareTaker();
	//private Comparator<Product> comparator;
	private String compareType;
	private boolean isFileEmpty;

	private static final long serialVersionUID = 1L;
	public static final String F_NAME = "products.txt";
	private File binFile = new File(F_NAME);

	private FileIterator fi = new FileIterator(F_NAME);

	public Store() throws Exception {
		if(binFile.length() != 0)
			isFileEmpty = false;
		else
			isFileEmpty = true;
		
		for (StoreListener l : allListeners)
			l.fireIsFileExistToView(isFileEmpty);	
		
		if (!isFileEmpty)
		{
			createSortedProducts(fi.readSortTypeFromFile());
			readProductsFromFile();
		}
			
	}

	public void setComparator(Object o) {
		setComparator(o);
	}

//	public Comparator<Product> getComparator() {
//		return comparator;
//	}

	public void createSortedProducts(String type) {
		
		switch (type) {
		case "Descending":
			products = new TreeMap<String, Product>(new CompareByCatalogNumDESC());
			setCompareType("Descending");
			break;
		case "Insertion":
			products = new TreeMap<String, Product>(new CompareByInsertionOrder());
			setCompareType("Insertion");
			break;
		default:
			products = new TreeMap<String, Product>();
			setCompareType("Ascending");
			break;
		}
		
		if (binFile.length() == 0)
			fi.writeSortTypeToFile(getCompareType());
	}
	
	
	
	
	
	

	public void addProductToStore(String name, String catalogNumber, int price, int priceOfSale, String customerName,
			String phoneNumber, boolean notifications) throws Exception {

		ct.save(new StoreMemento(this));
		Customer temp = new Customer(customerName, phoneNumber, notifications);
		Product product = new Product(name, catalogNumber, price, priceOfSale, temp);

		products.put(catalogNumber, product);
		writeProductToFile(product);
	}
	
	public void addProductFromFileToStore(String name, String catalogNumber, int price, int priceOfSale, String customerName,
			String phoneNumber, boolean notifications) throws Exception {

		ct.save(new StoreMemento(this));
		Customer temp = new Customer(customerName, phoneNumber, notifications);
		Product product = new Product(name, catalogNumber, price, priceOfSale, temp);

		products.put(catalogNumber, product);
		
		//writeProductToFile(product);
	}
	
	
	
	
	
	
	
	

	public Product getProduct(String catalogNumber) {

		if (products.containsKey(catalogNumber))
			return products.get(catalogNumber);
		else
			return null;
	}

	public void removeLastProductAdded() {
		load(ct.load());
		//fi.removeLastProduct();
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

	public TreeMap<String, Product> getProducts() {
		//System.out.println(products);
		return products;
	}
	
	public Vector<Product> getProductsToVector() {
		Vector<Product> productsVector = new Vector<Product>();
		for (Map.Entry<String, Product> entry : products.entrySet())
			productsVector.add(entry.getValue());
		
		//System.out.println(productsVector);
		return productsVector;		
	}

	public StoreMemento save() {
		return new StoreMemento(this);
	}

	public void load(StoreMemento sm) {
		products = sm.getProducts();
	}

	public void registerListeners(StoreListener newListener) {
		allListeners.add(newListener);
	}

	public void writeProductToFile(Product product) {
		// for (Map.Entry<String, Product> entry : products.entrySet())
		// fi.writeToFile(entry.getValue());
		fi.writeToFile(product);
	}

	public void readProductsFromFile() throws Exception {
		KeyValueProduct kv;
		kv = new KeyValueProduct((KeyValueProduct) fi.next());
		
		while (kv.getKey() != null) {
			addProductFromFileToStore(kv.getValue().getName(), kv.getKey(), kv.getValue().getPrice(),
					kv.getValue().getPriceOfSale(), kv.getValue().getCustomer().getName(),
					kv.getValue().getCustomer().getPhoneNumber(), kv.getValue().getCustomer().getNotifications());
			if(fi.hasNext()) {
				kv = new KeyValueProduct((KeyValueProduct) fi.next());
			}
			else {
				break;
			}
				
		}
	}

	public String getCompareType() {
		return compareType;
	}

	public void setCompareType(String compareType) {
		this.compareType = compareType;
	}
	
	public File getFile() {
		return binFile;
	}
	
	
	

}
