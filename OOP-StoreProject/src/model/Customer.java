package model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Customer implements Observer, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String phoneNumber;
	private Boolean notifications;
	
	public Customer(String name, String phoneNumber, Boolean notifications) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.notifications = notifications;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public Boolean getNotifications() {
		return notifications;
	}

	public void setNotifications(Boolean notifications) {
		this.notifications = notifications;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", phoneNumber=" + phoneNumber 
				+ ", notifications=" + notifications + "]";
	}

	@Override
	public void update(Observable obs, Object obj) {
		if(obs instanceof Sale)
			System.out.println(name + " Hi!\nNews Updated!\n" + obs);
	}


	
	
	
	
	
}
