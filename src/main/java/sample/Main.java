package main.java.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.sample.Controller;

public class Main extends Application {

    static Controller myConroll;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("samplee.fxml"));
        Parent root = loader.load();
        myConroll = (Controller)loader.getController();


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        myConroll.glav();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
