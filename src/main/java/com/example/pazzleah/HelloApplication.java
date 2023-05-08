package com.example.pazzleah;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("select_level.fxml"));
        Scene sceneMainMenu = new Scene(fxmlLoader1.load(), 1920, 1080);
        Scene sceneSelectLevel = new Scene(fxmlLoader2.load(), 1920, 1080);
        Pane pane = new Pane();
        pane.setBackground(Background.fill(Color.web("#F2F2AC", 1.0)));
        Scene sceneLvl = new Scene(pane, 1920, 1080);
        /*Scene sceneLvl2 = new Scene(pane, 1920, 1080);
        Scene sceneLvl3 = new Scene(pane, 1920, 1080);
        Scene sceneLvl4 = new Scene(pane, 1920, 1080);
        Scene sceneLvl5 = new Scene(pane, 1920, 1080);*/
        Button btnPlay = (Button) sceneMainMenu.lookup("#play");
        Button btnLvl1 = (Button) sceneSelectLevel.lookup("#lvl1");
        Button btnLvl2 = (Button) sceneSelectLevel.lookup("#lvl2");
        Button btnLvl3 = (Button) sceneSelectLevel.lookup("#lvl3");
        Button btnLvl4 = (Button) sceneSelectLevel.lookup("#lvl4");
        Button btnLvl5 = (Button) sceneSelectLevel.lookup("#lvl5");
        Button btnBackMain = (Button) sceneSelectLevel.lookup("#backMain");
        Button btnExit = (Button) sceneMainMenu.lookup("#exit");
       /* Lock lock1 = new Lock(
                new ArrayList<>(Arrays.asList(0,6 , 3, 2, 11, 6, 7, 8)),
                new ArrayList<>(Arrays.asList(1,0 , 3, 2, 11, 6, 7, 8)),
                sceneLvl, pane, sceneSelectLevel, stage);*/

        btnPlay.setOnMouseClicked(mouseEvent -> {
            stage.setScene(sceneSelectLevel);
        });

        btnLvl1.setOnMouseClicked(mouseEvent -> {
            stage.setScene(sceneLvl);
            new Lock(6,0,0, sceneLvl, pane, sceneSelectLevel, stage);

        });
        btnLvl2.setOnMouseClicked(mouseEvent -> {
            stage.setScene(sceneLvl);
            new Lock(3,2,0, sceneLvl, pane, sceneSelectLevel, stage);
        });
        btnLvl3.setOnMouseClicked(mouseEvent -> {
            stage.setScene(sceneLvl);
            new Lock(2,0,0, sceneLvl, pane, sceneSelectLevel, stage);
        });
        btnLvl4.setOnMouseClicked(mouseEvent -> {
            stage.setScene(sceneLvl);
            new Lock(1,2,0, sceneLvl, pane, sceneSelectLevel, stage);
        });
        btnLvl5.setOnMouseClicked(mouseEvent -> {
            stage.setScene(sceneLvl);
            new Lock(0,0,0, sceneLvl, pane, sceneSelectLevel, stage);
        });

        btnBackMain.setOnMouseClicked(mouseEvent -> {
            stage.setScene(sceneMainMenu);
        });
        btnExit.setOnMouseClicked(mouseEvent -> {
            stage.close();
        });
        stage.setScene(sceneMainMenu);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}