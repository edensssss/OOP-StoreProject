package ID_315685461_Eden_Schwartz_StoreProject;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ViewModel extends VBox {
	
	//headlines  texts nodes
	private Text text = new Text("Welcome to my store");
	private Text customerDetails = new Text("Customer datails: ");
	private Text productDetails = new Text("Product datails: ");
	
	//products nodes
	private Label labelProductName = new Label("Product name:    ");
	private Label labelProductNumber = new Label("Product number: ");
	private Label priceOfProduct = new Label("Product's price:   ");
	private Label priceOfProductSale = new Label("Product's price of sale:");
	
	//customer nodes
	private Label customerName = new Label("Customer name:               ");
	private Label customerPhoneNumber = new Label("Customer phone-number:");
	CheckBox checkBox = new CheckBox("Get notifications");
	
	//text fields
	private TextField tfProductName = new TextField();
	private TextField tfProductNumber = new TextField();
	private TextField tfPriceOfProduct = new TextField();
	private TextField tfPriceOfProductSale = new TextField();
	private TextField tfCustomerName = new TextField();
	private TextField tfCustomerPhoneNumber = new TextField();
	
	private Pane productNamePane = new HBox(20);
	private Pane productNumberPane = new HBox(20);
	private Pane pricesPane = new HBox(20);
	private Pane customerNamePane = new HBox(20);
	private Pane customerPhoneNumberPane = new HBox(20);
	private Pane customerDetailsPane = new VBox(20);
	private Pane buttonsPane = new HBox(20);
	
	private Button btnInsertProduct = new Button("INSERT NEW PRODUCT");
	private Button btnShowProductInfoByNumber = new Button("SHOW PRODUCT INFO BY NUMBER");
	private Button btnSshowAllProducts = new Button("SHOW ALL PRODUCTS");
	
	
	
	public ViewModel() {
		super();
		productNamePane.getChildren().addAll(labelProductName, tfProductName);
		productNumberPane.getChildren().addAll(labelProductNumber, tfProductNumber);
		buttonsPane.getChildren().addAll(btnInsertProduct, btnShowProductInfoByNumber, btnSshowAllProducts);
		pricesPane.getChildren().addAll(priceOfProduct, tfPriceOfProduct, priceOfProductSale, tfPriceOfProductSale);
		customerNamePane.getChildren().addAll(customerName, tfCustomerName);
		customerPhoneNumberPane.getChildren().addAll(customerPhoneNumber, tfCustomerPhoneNumber);
		customerDetailsPane.getChildren().addAll(customerNamePane, customerPhoneNumberPane, checkBox);
		
		/* design headline */
		text.setFill(Color.BLUEVIOLET);
		text.setStroke(Color.BLACK);
		text.setStyle("-fx-font: 40 Allan;");
		
		/* design buttonsPane */
		btnInsertProduct.setStyle("-fx-background-color: BLUEVIOLET");
		btnShowProductInfoByNumber.setStyle("-fx-background-color: BLUEVIOLET");
		btnSshowAllProducts.setStyle("-fx-background-color: BLUEVIOLET");
		btnInsertProduct.setTextFill(Color.BLACK);
		btnShowProductInfoByNumber.setTextFill(Color.BLACK);
		btnSshowAllProducts.setTextFill(Color.BLACK);
		buttonsPane.setPadding(new Insets(35));
		
		/* design headline of customerDetails & productDetails*/
		productDetails.setFill(Color.BLACK);
		productDetails.setStyle("-fx-font: 20 Allan;");
		customerDetails.setFill(Color.BLACK);
		customerDetails.setStyle("-fx-font: 20 Allan;");
	
		/* add all nodes to pane */
		getChildren().addAll(text, productDetails, productNamePane, productNumberPane, pricesPane, customerDetails, customerDetailsPane, buttonsPane);
		
		/* vBox pane design */
		setSpacing(20);
	
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Button getBtnInsertProduct() {
		return btnInsertProduct;
	}

	public void setBtnInsertProduct(Button btnInsertProduct) {
		this.btnInsertProduct = btnInsertProduct;
	}



	public Pane getButtonsPane() {
		return buttonsPane;
	}

	public void setButtonsPane(Pane buttonsPane) {
		this.buttonsPane = buttonsPane;
	}

}
