package model;

import java.util.Observable;

public class Sale extends Observable{

	private static Sale[] _instance = new Sale[1];
	private String value;
	
	public Sale() {
		
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		return "Sale [value=" + value + "]";
	}
	
	public static Sale getInstace(int i) {
		if(_instance[i]==null)
			_instance[i] = new Sale();
		return _instance[i];

	}
	

}

