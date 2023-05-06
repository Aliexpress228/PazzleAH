package com.example.pazzleah;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load(), 320, 240);
        Pane pane = new Pane();
        pane.setBackground(Background.fill(Color.web("#F2F2AC", 1.0)));
        Scene scene = new Scene(pane, 1920, 1080);

        Lock lock = new Lock(
                /*new ArrayList<>(Arrays.asList(5, 5, 7, 3, 1, 2, 4, 6)),
                new ArrayList<>(Arrays.asList(6, 2, 5, 4, 7, 1, 3, 5)),*/
                new ArrayList<>(Arrays.asList(0,6 , 3, 2, 11, 6, 7, 8)),
                new ArrayList<>(Arrays.asList(1,0 , 3, 2, 11, 6, 7, 8)),
                //new ArrayList<>(Arrays.asList(7,3,1,2,4,6,5,5)),
                scene, pane);

        lock.setCirclesArray(lock.cirArr, lock);
        lock.setIndicatorsArray(lock.indicatorsArr, lock);
        ArrayList<Boolean> pressed= new ArrayList<>();
        pressed.add(false);
        scene.setOnKeyPressed(e -> {
            if (!pressed.get(0)) {
                if (e.getCode() == KeyCode.A) {
                    lock.rotateLock(-2);
                }
                if (e.getCode() == KeyCode.D) {
                    lock.rotateLock(2);
                }
                if (e.getCode() == KeyCode.S) {
                    lock.rotateTriangle();
                }
                if (lock.isCompleted()) {
                    System.out.println("ready");
                    stage.close();
                }
                pressed.set(0, true);
            }
        });

        scene.setOnKeyReleased(e -> {
            pressed.set(0, false);
        });

        lock.left.setOnMouseClicked(mouseEvent -> {
            lock.rotateLock(-2);
            if (lock.isCompleted()) {
                System.out.println("ready");
                stage.close();
            }
        });

        lock.right.setOnMouseClicked(mouseEvent -> {
            lock.rotateLock(2);
            if (lock.isCompleted()) {
                System.out.println("ready");
                stage.close();
            }
        });

        lock.swap.setOnMouseClicked(mouseEvent -> {
            lock.rotateTriangle();
            lock.setCirclesArray(lock.cirArr, lock);
            lock.setIndicatorsArray(lock.indicatorsArr, lock);
            if (lock.isCompleted()) {
                System.out.println("ready");
                stage.close();
            }
        });
        /*AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long l) {
                lock.getIndicatorsArr();

            }
        };
        at.start();*/
        stage.setScene(scene2);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}