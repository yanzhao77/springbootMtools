package com.example.markdemofx.old;

/**
 * @author yanzhao
 * @version 1.0
 * @classname asd
 * @date 2023/05/12 13:47
 * @description TODO
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class NumberAxisMain extends Application {

    @Override
    public void start(Stage stage) {

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X Axis");
        yAxis.setLabel("Y Axis");

        //creating the chart
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Sin 45");

        //populating the series with data
        for (int i = 0; i <= 500; i++) {
            double x = Math.toRadians(i);
            double y = Math.sin(x) * Math.sqrt(2) / 2;
            series.getData().add(new XYChart.Data(x, y));
        }

        //adding the series to the chart
        lineChart.getData().add(series);

        //setting the scene
        Scene scene = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}