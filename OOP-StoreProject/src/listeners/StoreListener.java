package listeners;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Vector;

import model.Customer;
import model.Product;


public interface StoreListener {
	
	
	void fireProductToUI(Product product);
	
	
	void fireCustomersToUI(ArrayList<Customer> customers);
	
	//add functions here
	
	

	/*
	 * void fireParticipantsToUI(Vector<Participant> participants);
	 * 
	 * void fireAddedPlayerToUI(String name);
	 * 
	 * void startTournamentInModel(String gameType);
	 * 
	 * void fireNextTextFieldsToUI(int partNum);
	 * 
	 * void fireWinnerToUI(String name, int phaseIndex, int gameIndex);
	 */
	
}
