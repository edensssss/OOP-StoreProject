package ID_315685461_Eden_Schwartz_StoreProject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.HashMap;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainView extends Application {

	// create UI
	ViewModel viewModel = new ViewModel();

	// create store
	Store store = new Store();

	// Declare output and input stream for file products.txt
	ObjectOutputStream output;
	ObjectInputStream input;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		/* design the viewModel and add to the scene */
		viewModel.setPadding(new Insets(20));
		viewModel.setPrefSize(650, 400);
		Scene scene = new Scene(viewModel);

		/* init file */
		output = new ObjectOutputStream(new FileOutputStream("products.txt"));
		input = new ObjectInputStream(new FileInputStream("products.txt"));

		/* insert product - button action */
		viewModel.getBtnInsertProduct().setOnAction(e -> {
			if (viewModel.getTfProductNumber().getText().length() == 0) {
				viewModel.status.setFill(Color.RED);
				viewModel.status.setText(" Product Number can not be empty.");
			} else {
				try {
					store.addProduct(viewModel, output, input);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}

		});
		
		/* show product by number - button action */
		viewModel.getBtnShowProductInfoByNumber().setOnAction(e -> {
			if (viewModel.getTfProductNumber().getText().length() == 0) {
				viewModel.status.setFill(Color.RED);
				viewModel.status.setText(" Product Number can not be empty.");
			} else {
				store.showProductInfoByNumber(viewModel, output, input);
			}

		});
		
		/* show all products - button action */
		viewModel.getBtnSshowAllProducts().setOnAction(e -> {
			
			store.showAllProducts(viewModel, output, input);

		});
		
		

		/* placing the scene in the stage */
		primaryStage.centerOnScreen();
		primaryStage.setScene(scene);
		primaryStage.setTitle("DisplayMovingMessage");
		primaryStage.alwaysOnTopProperty();
		primaryStage.setResizable(false);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.show();

	}

}
