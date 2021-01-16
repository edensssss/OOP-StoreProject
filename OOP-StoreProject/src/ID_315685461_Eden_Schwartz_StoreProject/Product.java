package ID_315685461_Eden_Schwartz_StoreProject;

import java.io.Serializable;

public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String number;
	private int price;
	private int priceOfSale;
	private Customer customer;

	

	public Product(String name, String number, int price, int priceOfSale, Customer customer) {
		super();
		this.name = name;
		this.number = number;
		this.price = price;
		this.priceOfSale = priceOfSale;
		this.customer = customer;
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
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
		return "Product [name=" + name + ", number=" + number + ", price=" + price + ", priceOfSale=" + priceOfSale
				+ "]";
	}
	
	

}
