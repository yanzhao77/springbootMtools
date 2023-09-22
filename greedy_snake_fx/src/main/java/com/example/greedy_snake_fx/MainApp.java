package com.example.greedy_snake_fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(500,500);
//        anchorPane.getChildren().add(canvas);


        canvas.setHeight(anchorPane.getHeight());
        canvas.setWidth(anchorPane.getWidth());
        canvas.setStyle("-fx-background-color: blue");
        System.out.println(canvas.getHeight());
        System.out.println(canvas.getWidth());
        primaryStage.show();
    }
}
