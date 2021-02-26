package listeners;

import java.util.TreeMap;

import model.Product;

public interface ViewListener {
	
	public void selectionSortToModel(String type);

	public void addProductToModel(String name, String catalogNumber, int price, 
			int priceOfSale, String customerName, String phoneNumber, boolean notifications);
	
	public void loadProductToModel(String catalogNumber);
	
	public void showProductsToModel();
	
	public void removeLastProductAddedToModel();
	
	public void showProfitToModel();
	
	public void sendMessageToModel(String saleMessage);
	

	//add functions here
	
	
	/*
	 * public void submitPartScoreInModel(int gameIndex,int scoreTeamA,int
	 * scoreTeamB);
	 * 
	 * public void addParticipantToModel(String text);
	 * 
	 * public void startGameInModel(int gameIndex);
	 * 
	 * public void startTournamentInModel(String selectedGame);
	 */

}
