package listeners;

public interface ViewListener {
	
	

	public void addProductToModel(String name, String catalogNumber, int price, 
			int priceOfSale, String customerName, String phoneNumber, boolean notifications);
	
	public void loadProductToModel(String catalogNumber);
	
	

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
