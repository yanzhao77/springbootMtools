package com.example.greedy_snake_fx;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreedySnakeFxApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GreedySnakeFxApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Application.launch(MainApp.class, args);
    }
}
