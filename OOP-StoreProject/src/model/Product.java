package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;


public class Product implements Comparable<Product>, Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String catalogNumber;
	private int price;
	private int priceOfSale;
	private Customer customer;

	public Product(String name, String number, int price, int priceOfSale, Customer customer) {
		super();
		this.name = name;
		this.catalogNumber = number;
		this.price = price;
		this.priceOfSale = priceOfSale;
		this.customer = customer;
	}

	public Product(RandomAccessFile raf) {
		try {
			this.catalogNumber = raf.readUTF();
			this.name = raf.readUTF();
			this.price = raf.readInt();
			this.priceOfSale = raf.readInt();		
			this.customer = new Customer(raf);
		} catch (FileNotFoundException e) {
			System.out.println("DeleteStrFromFileMethodException: File Not Found! " + e.getMessage());
		} catch (IOException e) {
			System.out.println("DeleteStrFromFileMethodException: Input Output Exception! " + e.getMessage());
		}		
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return catalogNumber;
	}

	public void setNumber(String number) {
		this.catalogNumber = number;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;

	}

	public int getPriceOfSale() {
		return priceOfSale;
	}

	public void setPriceOfSale(int priceOfSale) {
		this.priceOfSale = priceOfSale;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\nCatalog num: " + catalogNumber + "\nPrice: " + price + "         Price sale: " + priceOfSale + "\n" + customer.toString();
	}

	@Override
	public int compareTo(Product o) {
		return this.catalogNumber.compareTo(o.catalogNumber);
	}

}
