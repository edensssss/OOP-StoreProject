package ID_315685461_Eden_Schwartz_StoreProject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ViewModel extends VBox {

	// headlines texts nodes
	private Text text = new Text("Welcome to my store");
	private Text customerDetails = new Text("Customer datails: ");
	private Text productDetails = new Text("Product datails: ");
	private Text storeStatus = new Text("Store status: ");
	Text status = new Text("");

	// products nodes
	private Label labelProductName = new Label("Product name:    ");
	private Label labelProductNumber = new Label("Product number: ");
	private Label priceOfProduct = new Label("Product's price:   ");
	private Label priceOfProductSale = new Label("Product's price of sale:");

	// customer nodes
	private Label customerName = new Label("Customer name:               ");
	private Label customerPhoneNumber = new Label("Customer phone-number:");
	CheckBox checkBox = new CheckBox("Get notifications");

	// text fields
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
	private Pane userPane = new HBox(20);

	private Button btnInsertProduct = new Button("INSERT NEW PRODUCT");
	private Button btnShowProductInfoByNumber = new Button("SHOW PRODUCT INFO BY NUMBER");
	private Button btnSshowAllProducts = new Button("SHOW ALL PRODUCTS");

	public ViewModel() {
		super();

		userPane.getChildren().addAll(status);
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

		/* design user massages pane */
		userPane.setStyle("-fx-border-color: black");
		userPane.setMinHeight(100);
		status.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

		/* design buttonsPane */
		btnInsertProduct.setTextFill(Color.BLACK);
		btnShowProductInfoByNumber.setTextFill(Color.BLACK);
		btnSshowAllProducts.setTextFill(Color.BLACK);
		buttonsPane.setPadding(new Insets(25));

		/* design headline of customerDetails & productDetails */
		productDetails.setFill(Color.BLACK);
		productDetails.setStyle("-fx-font: 20 Allan;");
		customerDetails.setFill(Color.BLACK);
		customerDetails.setStyle("-fx-font: 20 Allan;");
		storeStatus.setFill(Color.BLACK);
		storeStatus.setStyle("-fx-font: 20 Allan;");

		/* add all nodes to pane */
		getChildren().addAll(text, productDetails, productNamePane, productNumberPane, pricesPane, customerDetails,
				customerDetailsPane, userPane, buttonsPane);

		/* vBox pane design */
		setSpacing(20);

		/* to able the use of number only in textField: tfPriceOfProduct */
		tfPriceOfProduct.setTextFormatter(new TextFormatter<>(c -> {
			if (!c.getControlNewText().matches("\\d*")) {
				// user message
				status.setFill(Color.RED);
				status.setText(" Error: Price can contain numbers only.");
				return null;
			}
			else {
				status.setText(" ");
				return c;
			}

		}));

		/* to able the use of number only in textField: tfPriceOfProduct */
		tfPriceOfProductSale.setTextFormatter(new TextFormatter<>(c -> {
			if (!c.getControlNewText().matches("\\d*")) {
				// user message
				status.setFill(Color.RED);
				status.setText(" Error: Price can contain numbers only.");
				return null;
			}
			else {
				status.setText(" ");
				return c;
			}
		}));

	}

	public Text getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(Text storeStatus) {
		this.storeStatus = storeStatus;
	}

	public Text getStatus() {
		return status;
	}

	public void setStatus(Text status) {
		this.status = status;
	}

	public Pane getUserPane() {
		return userPane;
	}

	public void setUserPane(Pane userPane) {
		this.userPane = userPane;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Text getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(Text customerDetails) {
		this.customerDetails = customerDetails;
	}

	public Text getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Text productDetails) {
		this.productDetails = productDetails;
	}

	public Label getLabelProductName() {
		return labelProductName;
	}

	public void setLabelProductName(Label labelProductName) {
		this.labelProductName = labelProductName;
	}

	public Label getLabelProductNumber() {
		return labelProductNumber;
	}

	public void setLabelProductNumber(Label labelProductNumber) {
		this.labelProductNumber = labelProductNumber;
	}

	public Label getPriceOfProduct() {
		return priceOfProduct;
	}

	public void setPriceOfProduct(Label priceOfProduct) {
		this.priceOfProduct = priceOfProduct;
	}

	public Label getPriceOfProductSale() {
		return priceOfProductSale;
	}

	public void setPriceOfProductSale(Label priceOfProductSale) {
		this.priceOfProductSale = priceOfProductSale;
	}

	public Label getCustomerName() {
		return customerName;
	}

	public void setCustomerName(Label customerName) {
		this.customerName = customerName;
	}

	public Label getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(Label customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}

	public TextField getTfProductName() {
		return tfProductName;
	}

	public void setTfProductName(TextField tfProductName) {
		this.tfProductName = tfProductName;
	}

	public TextField getTfProductNumber() {
		return tfProductNumber;
	}

	public void setTfProductNumber(TextField tfProductNumber) {
		this.tfProductNumber = tfProductNumber;
	}

	public TextField getTfPriceOfProduct() {
		return tfPriceOfProduct;
	}

	public void setTfPriceOfProduct(TextField tfPriceOfProduct) {
		this.tfPriceOfProduct = tfPriceOfProduct;
	}

	public TextField getTfPriceOfProductSale() {
		return tfPriceOfProductSale;
	}

	public void setTfPriceOfProductSale(TextField tfPriceOfProductSale) {
		this.tfPriceOfProductSale = tfPriceOfProductSale;
	}

	public TextField getTfCustomerName() {
		return tfCustomerName;
	}

	public void setTfCustomerName(TextField tfCustomerName) {
		this.tfCustomerName = tfCustomerName;
	}

	public TextField getTfCustomerPhoneNumber() {
		return tfCustomerPhoneNumber;
	}

	public void setTfCustomerPhoneNumber(TextField tfCustomerPhoneNumber) {
		this.tfCustomerPhoneNumber = tfCustomerPhoneNumber;
	}

	public Pane getProductNamePane() {
		return productNamePane;
	}

	public void setProductNamePane(Pane productNamePane) {
		this.productNamePane = productNamePane;
	}

	public Pane getProductNumberPane() {
		return productNumberPane;
	}

	public void setProductNumberPane(Pane productNumberPane) {
		this.productNumberPane = productNumberPane;
	}

	public Pane getPricesPane() {
		return pricesPane;
	}

	public void setPricesPane(Pane pricesPane) {
		this.pricesPane = pricesPane;
	}

	public Pane getCustomerNamePane() {
		return customerNamePane;
	}

	public void setCustomerNamePane(Pane customerNamePane) {
		this.customerNamePane = customerNamePane;
	}

	public Pane getCustomerPhoneNumberPane() {
		return customerPhoneNumberPane;
	}

	public void setCustomerPhoneNumberPane(Pane customerPhoneNumberPane) {
		this.customerPhoneNumberPane = customerPhoneNumberPane;
	}

	public Pane getCustomerDetailsPane() {
		return customerDetailsPane;
	}

	public void setCustomerDetailsPane(Pane customerDetailsPane) {
		this.customerDetailsPane = customerDetailsPane;
	}

	public Pane getButtonsPane() {
		return buttonsPane;
	}

	public void setButtonsPane(Pane buttonsPane) {
		this.buttonsPane = buttonsPane;
	}

	public Button getBtnInsertProduct() {
		return btnInsertProduct;
	}

	public void setBtnInsertProduct(Button btnInsertProduct) {
		this.btnInsertProduct = btnInsertProduct;
	}

	public Button getBtnShowProductInfoByNumber() {
		return btnShowProductInfoByNumber;
	}

	public void setBtnShowProductInfoByNumber(Button btnShowProductInfoByNumber) {
		this.btnShowProductInfoByNumber = btnShowProductInfoByNumber;
	}

	public Button getBtnSshowAllProducts() {
		return btnSshowAllProducts;
	}

	public void setBtnSshowAllProducts(Button btnSshowAllProducts) {
		this.btnSshowAllProducts = btnSshowAllProducts;
	}

	public void clearTextFields() {
		tfCustomerName.clear();
		tfCustomerPhoneNumber.clear();
		tfPriceOfProduct.clear();
		tfPriceOfProductSale.clear();
		tfProductName.clear();
		tfProductNumber.clear();
	}

}
