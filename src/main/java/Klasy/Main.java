package Klasy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/core/sample.fxml"));
        primaryStage.setTitle("EP Sap Center");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
        primaryStage.getIcons().add(new Image("/images/ikona.PNG"));
        primaryStage.setResizable(false);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
