package view;

import java.util.Vector;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import listeners.ViewListener;

public class StoreView extends Application {

	private static Vector<ViewListener> allListeners = new Vector<ViewListener>();

	Boolean selectionByUser = true;

	// main pane: VBox
	StackPane stackPane = new StackPane();
	VBox mainPane = new VBox();
//	VBox selectSort = new VBox();

	// headlines texts nodes
	Text text = new Text("Welcome to the store");
	Text customerDetails = new Text("Customer datails: ");
	Text productDetails = new Text("Product datails: ");
	Text storeStatus = new Text("Store status: ");
	Text status = new Text("");
	Text selection = new Text("Please select sort type");

	// products nodes
	Label labelProductName = new Label("Product name:                  ");
	Label labelProductNumber = new Label("Product number:              ");
	Label priceOfProduct = new Label("Product's price:                ");
	Label priceOfProductSale = new Label("Product's price of sale:     ");
	Label labelMessege = new Label("Enter messege here: ");

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
	static TextField tfMessege = new TextField();

	// scroll bar for output
	ScrollPane scrollBar = new ScrollPane();

	// Radio buttons
	ToggleGroup tglSort = new ToggleGroup();
	RadioButton rbASC = new RadioButton("Ascending");
	RadioButton rbDESC = new RadioButton("Descending");
	RadioButton rbInsert = new RadioButton("Insertion");

	Pane messegePane = new HBox(40);
	Pane hBPane = new HBox(40);
	Pane textFieldsPane = new VBox(20);
	Pane rbPane = new VBox(40);
	Pane productNamePane = new HBox(20);
	Pane productNumberPane = new HBox(20);
	Pane productPricePane = new HBox(20);
	Pane productPriceOfSalePane = new HBox(20);
	Pane productDetailsPane = new VBox(20);
	Pane customerNamePane = new HBox(20);
	Pane customerPhoneNumberPane = new HBox(20);
	Pane customerDetailsPane = new VBox(20);
	Pane buttonsPane = new HBox(20);
	Pane userPane = new HBox(20);
	Pane rightPane = new VBox(20);

	Button btnInsertProduct = new Button("Insert New Product");
	Button btnShowProductInfoByNumber = new Button("Search Product");
	Button btnShowAllProducts = new Button("Show All Products");
	Button btnRemoveLastProductAdded = new Button("Remove Last Product");
	Button btnShowProfit = new Button("Show profit");
	Button btnSendMessage = new Button("Send Message");
	Button btnSubmitSort = new Button("Submit");

	// label for exeptions
	Label lblException = new Label();

	private Stage primaryStage;

	public StoreView(Stage primaryStage) {

		// creating tgl group for RBs
		rbASC.setToggleGroup(tglSort);
		rbDESC.setToggleGroup(tglSort);
		rbInsert.setToggleGroup(tglSort);

		// scroll bar
		scrollBar.setContent(lblException);
		scrollBar.setPrefSize(400, 350);

		activateStartingScreen();
		productNamePane.getChildren().addAll(labelProductName, tfProductName);
		productNumberPane.getChildren().addAll(labelProductNumber, tfProductNumber);
		productDetailsPane.getChildren().addAll(productDetails, productNamePane, productNumberPane, productPricePane,
				productPriceOfSalePane);
		productPricePane.getChildren().addAll(priceOfProduct, tfPriceOfProduct);
		productPriceOfSalePane.getChildren().addAll(priceOfProductSale, tfPriceOfProductSale);
		rbPane.getChildren().addAll(selection, rbASC, rbDESC, rbInsert, btnSubmitSort);
		userPane.getChildren().addAll(scrollBar, messegePane);
		messegePane.getChildren().addAll(labelMessege, tfMessege);
		rightPane.getChildren().addAll(userPane, messegePane);
		buttonsPane.getChildren().addAll(btnInsertProduct, btnShowProductInfoByNumber, btnShowAllProducts,
				btnRemoveLastProductAdded, btnShowProfit, btnSendMessage);
		customerNamePane.getChildren().addAll(customerName, tfCustomerName);
		customerPhoneNumberPane.getChildren().addAll(customerPhoneNumber, tfCustomerPhoneNumber);
		customerDetailsPane.getChildren().addAll(customerNamePane, customerPhoneNumberPane, checkBox);

		/* design rbPane */
		selection.setStyle("-fx-font: 16 Allan;");

		/* design headline */
		text.setFill(Color.ROYALBLUE);
		text.setStroke(Color.BLACK);
		text.setStyle("-fx-font: 40 Allan;");

		/* design user massages pane */
//		userPane.setStyle("-fx-border-color: black");
		// userPane.setMinHeight(350);
		// userPane.setMinWidth(400);
		status.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

		/* design buttonsPane */
		btnInsertProduct.setTextFill(Color.BLACK);
		btnShowProductInfoByNumber.setTextFill(Color.BLACK);
		btnShowAllProducts.setTextFill(Color.BLACK);
		btnRemoveLastProductAdded.setTextFill(Color.BLACK);
		btnShowProfit.setTextFill(Color.BLACK);
		btnSendMessage.setTextFill(Color.BLACK);
		buttonsPane.setPadding(new Insets(25));

		/* design headline of customerDetails & productDetails */
		productDetails.setFill(Color.BLACK);
		productDetails.setStyle("-fx-font: 20 Allan;");
		customerDetails.setFill(Color.BLACK);
		customerDetails.setStyle("-fx-font: 20 Allan;");
		storeStatus.setFill(Color.BLACK);
		storeStatus.setStyle("-fx-font: 20 Allan;");

		/* add all nodes to pane */
		mainPane.getChildren().addAll(text, hBPane, buttonsPane);
		hBPane.getChildren().addAll(textFieldsPane, rightPane);
		textFieldsPane.getChildren().addAll(productDetails, productDetailsPane, customerDetails, customerDetailsPane);

		mainPane.setAlignment(Pos.CENTER);

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

		btnSubmitSort.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				RadioButton selected = (RadioButton) tglSort.getSelectedToggle();
				String sort = selected.getText();

				for (ViewListener l : allListeners) {
					l.selectionSortToModel(sort);
				}
				
				stackPane.getChildren().addAll(mainPane);
				rbPane.setVisible(false);
				

			}
		});

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
					// clear after insert
					tfProductName.clear();
					tfProductNumber.clear();
					tfPriceOfProduct.clear();
					tfPriceOfProductSale.clear();

					tfCustomerName.clear();
					tfCustomerPhoneNumber.clear();
					checkBox.setSelected(false);
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
				} else {
					lblException.setText("Product number can't be empty");
					lblException.setVisible(true);
				}
			}
		});

		btnShowAllProducts.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				for (ViewListener l : allListeners) {
					l.showProductsToModel();
				}

			}
		});

		btnRemoveLastProductAdded.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				for (ViewListener l : allListeners) {
					l.removeLastProductAddedToModel();
				}

			}
		});

		btnShowProfit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				for (ViewListener l : allListeners) {
					l.showProfitToModel();
				}

			}
		});

		btnSendMessage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				for (ViewListener l : allListeners) {
					l.sendMessageToModel(tfMessege.getText());
				}

				Label customers[] = getLblCustomers();
				System.out.println(customers);
				Thread tr = new Thread(() -> {
					try {
						for (Label l : customers) {
							Thread.sleep(2000);
							Platform.runLater(() -> {
								l.setVisible(true);
							});
						}
					} catch (Exception e) {
					}
				});
				tr.start();
			}
		});

		Scene scene = new Scene(stackPane, 830, 650);
		 
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.alwaysOnTopProperty();
		primaryStage.setTitle("DisplayMovingMessage");
		primaryStage.show();

	}

	private void activateStartingScreen() {

		stackPane.getChildren().addAll(rbPane);
		rbPane.setPadding(new Insets(100));
		// getting selection and activate the main screen by:
	//	 stackPane.getChildren().addAll(mainPane);

	}

	
	
	public ScrollPane getScrollBar() {
		return scrollBar;
	}

	public void setScrollBar(ScrollPane scrollBar) {
		this.scrollBar = scrollBar;
	}

	public void createLblCustomers(int num) {
		Label lblCustomerMsg[] = new Label[num];
	}

	public Label[] getLblCustomers() {
		return getLblCustomers();
	}

	public void createNewMsg(int index, String msg) {
		getLblCustomers()[index] = new Label(msg);
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
		return customerDetailsPane;
	}

	public void setPricesPane(Pane pricesPane) {
		this.customerDetailsPane = pricesPane;
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
		return btnShowAllProducts;
	}

	public Button getBtnRemoveLastProductAdded() {
		return btnRemoveLastProductAdded;
	}

	public void setBtnSshowAllProducts(Button btnSshowAllProducts) {
		this.btnShowAllProducts = btnSshowAllProducts;
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

	public void setLblException(String string) {
		lblException.setText(string);
	}

}
