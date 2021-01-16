package ID_315685461_Eden_Schwartz_StoreProject;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {

	ViewModel viewModel = new ViewModel();
	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		/* Designing the myPane */
		viewModel.setPadding(new Insets(20));
		viewModel.setPrefSize(650, 400);


		Scene scene = new Scene(viewModel);


		/* placing the scene in the stage */
		primaryStage.centerOnScreen();
		primaryStage.setScene(scene);
		primaryStage.setTitle("DisplayMovingMessage");
		primaryStage.alwaysOnTopProperty();
		primaryStage.setResizable(false);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.show();
		
		/* insert product button action */
		 viewModel.getBtnInsertProduct().setOnAction(e ->
	        {
	            // insert product
	        });
	}


}
