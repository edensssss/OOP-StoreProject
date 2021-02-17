package view;

import java.util.Vector;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
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
import javafx.stage.Stage;
import listeners.ViewListener;

public class StoreView extends Application {

	private static Vector<ViewListener> allListeners = new Vector<ViewListener>();

	// main pane: VBox
	VBox mainPane = new VBox();

	// headlines texts nodes
	Text text = new Text("Welcome to my store");
	Text customerDetails = new Text("Customer datails: ");
	Text productDetails = new Text("Product datails: ");
	Text storeStatus = new Text("Store status: ");
	Text status = new Text("");

	// products nodes
	Label labelProductName = new Label("Product name:    ");
	Label labelProductNumber = new Label("Product number: ");
	Label priceOfProduct = new Label("Product's price:   ");
	Label priceOfProductSale = new Label("Product's price of sale:");

	// customer nodes
	Label customerName = new Label("Customer name:               ");
	Label customerPhoneNumber = new Label("Customer phone-number:");
	CheckBox checkBox = new CheckBox("Get notifications");

	// text fields
	static TextField tfProductName = new TextField();
	static TextField tfProductNumber = new TextField();
	static TextField tfPriceOfProduct = new TextField();
	static TextField tfPriceOfProductSale = new TextField();
	static TextField tfCustomerName = new TextField();
	static TextField tfCustomerPhoneNumber = new TextField();

	Pane productNamePane = new HBox(20);
	Pane productNumberPane = new HBox(20);
	Pane pricesPane = new HBox(20);
	Pane customerNamePane = new HBox(20);
	Pane customerPhoneNumberPane = new HBox(20);
	Pane customerDetailsPane = new VBox(20);
	Pane buttonsPane = new HBox(20);
	Pane userPane = new HBox(20);

	Button btnInsertProduct = new Button("INSERT NEW PRODUCT");
	Button btnShowProductInfoByNumber = new Button("SHOW PRODUCT INFO BY NUMBER");
	Button btnSshowAllProducts = new Button("SHOW ALL PRODUCTS");

	// label for exeptions
	Label lblException = new Label();

	public StoreView(Stage primaryStage) {

		userPane.getChildren().addAll(lblException);
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
		mainPane.getChildren().addAll(text, productDetails, productNamePane, productNumberPane, pricesPane,
				customerDetails, customerDetailsPane, userPane, buttonsPane);

		/* vBox pane design */
		mainPane.setSpacing(20);
		mainPane.setPadding(new Insets(20));

		/* to able the use of number only in textField: tfPriceOfProduct */
		tfPriceOfProduct.setTextFormatter(new TextFormatter<>(c -> {
			if (!c.getControlNewText().matches("\\d*")) {
				// user message
				status.setFill(Color.RED);
				status.setText(" Error: Price can contain numbers only.");
				return null;
			} else {
				status.setText(" ");
				return c;
			}

		}));

		/* to able the use of number only in textField: tfPriceOfProduct */
		tfPriceOfProductSale.setTextFormatter(new TextFormatter<>(c -> {
			if (!c.getControlNewText().matches("\\d*")) {
				// user message
				lblException.setText(" Error: Price can contain numbers only.");
				return null;
			} else {
				lblException.setVisible(false);
				return c;
			}
		}));

		// Main stage button handler
		btnInsertProduct.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				// get TextFields
				String name = tfProductName.getText();
				String catalogProduct = tfProductNumber.getText();
				int price = Integer.parseInt(getTfPriceOfProduct().getText());
				int priceOfSale = Integer.parseInt(getTfPriceOfProductSale().getText());

				String customerName = tfCustomerName.getText();
				String phoneNumber = getTfCustomerPhoneNumber().getText();
				boolean notifications = checkBox.isSelected();

				if (!tfProductNumber.getText().isEmpty()) {
					lblException.setVisible(false);
					for (ViewListener l : allListeners) {
						l.addProductToModel(name, catalogProduct, price, priceOfSale, customerName, phoneNumber,
								notifications);
					}
					// tfPlayerName.clear();
				} else {
					lblException.setText("Product number can't be empty");
					lblException.setVisible(true);
				}
			}
		});

		// loadProductToModel

		btnShowProductInfoByNumber.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				String catalogProduct = tfProductNumber.getText();

				if (!tfProductNumber.getText().isEmpty()) {
					lblException.setVisible(false);
					for (ViewListener l : allListeners) {
						l.loadProductToModel(catalogProduct);
					}
					// tfPlayerName.clear();
				} else {
					lblException.setText("Product number can't be empty");
					lblException.setVisible(true);
				}
			}
		});

		/*
		 * insert product - button action btnInsertProduct().setOnAction(e -> {
		 * 
		 * 
		 * //add to the listeners
		 * 
		 * });
		 * 
		 * show product by number - button action
		 * btnShowProductInfoByNumber().setOnAction(e -> {
		 * 
		 * //add to the listeners
		 * 
		 * 
		 * });
		 * 
		 * show all products - button action btnShowAllProducts().setOnAction(e -> {
		 * 
		 * //add to the listeners
		 * 
		 * });
		 */

		// Creating Scenes
		Scene scene = new Scene(mainPane, 650, 700);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.alwaysOnTopProperty();
		primaryStage.setTitle("DisplayMovingMessage");
		primaryStage.show();

	}

	private ButtonBase btnInsertProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	private ButtonBase btnShowProductInfoByNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	private ButtonBase btnShowAllProducts() {
		// TODO Auto-generated method stub
		return null;
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

	public void setCheckBox(Boolean booleanNotifications) {
		this.checkBox.setSelected(booleanNotifications);
	}

	public static TextField getTfProductName() {
		return tfProductName;
	}

	public void setTfProductName(TextField tfProductName) {
		this.tfProductName = tfProductName;
	}

	public void setTfProductName(String tfProductName) {
		this.tfProductName.setText(tfProductName);
	}

	public TextField getTfProductNumber() {
		return tfProductNumber;
	}

	public void setTfProductNumber(TextField tfProductNumber) {
		this.tfProductNumber = tfProductNumber;
	}

	public static TextField getTfPriceOfProduct() {
		return tfPriceOfProduct;
	}

	public void setTfPriceOfProduct(int price) {
		this.tfPriceOfProduct.setText(Integer.toString(price));
	}

	public static TextField getTfPriceOfProductSale() {
		return tfPriceOfProductSale;
	}

	public void setTfPriceOfProductSale(int priceOfSale) {
		this.tfPriceOfProductSale.setText(Integer.toString(priceOfSale));
		;
	}

	public TextField getTfCustomerName() {
		return tfCustomerName;
	}

	public void setTfCustomerName(TextField tfCustomerName) {
		this.tfCustomerName = tfCustomerName;
	}

	public void setTfCustomerName(String tfCustomerName) {
		this.tfCustomerName.setText(tfCustomerName); // set the text in the tf
	}

	public TextField getTfCustomerPhoneNumber() {
		return tfCustomerPhoneNumber;
	}

	public void setTfCustomerPhoneNumber(String phoneNumber) {
		this.tfCustomerPhoneNumber.setText(phoneNumber);
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

	public void registerListeners(ViewListener newListener) {
		allListeners.add(newListener);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

	public Label getLblException() {
		return lblException;
	}

}
