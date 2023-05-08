package com.example.pazzleah;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Lock {

    Random rand = new Random();
    ArrayList<Integer> cellsColorIndexes;
    ArrayList<Integer> placesColorIndexes;
    ArrayList<Circle> cirArr;
    ArrayList<Circle> indicatorsArr = new ArrayList<>();
    ArrayList<Boolean> pressed= new ArrayList<>();
    Circle bigCircle = new Circle();
    Circle smallCircle = new Circle();
    ArrayList<CubicCurve> triangle = new ArrayList<>();
    Button left = new Button();
    Button right = new Button();
    Button swap = new Button();
    Button back = new Button();
    Text aText = new Text();
    Text dText = new Text();
    Text sText = new Text();
    Scene scene;

    double triangleRotateDistance;
    double getTriangleRotateStartingPoint;
    Lock(int emptyCells, int sameCells1, int sameCells2, Scene scene, Pane pane, Scene sceneBack, Stage stage) {
        pane.getChildren().clear();
        this.scene = scene;
        ArrayList<Integer> a1 = entanglement(generateComposition(emptyCells, sameCells1, sameCells2));
        ArrayList<Integer> a2 = entanglement(new ArrayList<>(a1));
        this.placesColorIndexes = a1;
        this.cellsColorIndexes = a2;
        this.fillLock( scene,  pane,  sceneBack, stage);
        setCirclesArray(cirArr, this);
        setActivities(scene, pane, sceneBack, stage);
    }

    private void fillLock(Scene scene, Pane pane, Scene sceneBack, Stage stage){
        pane.getChildren().add(left);
        pane.getChildren().add(right);
        pane.getChildren().add(swap);
        pane.getChildren().add(back);
        pane.getChildren().add(aText);
        pane.getChildren().add(dText);
        pane.getChildren().add(sText);

        left.setPrefWidth(70);
        left.setPrefHeight(70);
        left.setText("A");
        left.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        right.setPrefWidth(70);
        right.setPrefHeight(70);
        right.setText("D");
        right.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        swap.setPrefWidth(70);
        swap.setPrefHeight(70);
        swap.setText("S");
        swap.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        left.setLayoutX(scene.getWidth() * 0.25 - left.getPrefWidth() / 2.0);
        left.setLayoutY(scene.getHeight() * 0.9 - left.getPrefHeight() / 2.0);

        right.setLayoutX(scene.getWidth() * 0.75 - right.getPrefWidth() / 2.0);
        right.setLayoutY(scene.getHeight() * 0.9 - right.getPrefHeight() / 2.0);

        swap.setLayoutX(scene.getWidth() / 2.0 - swap.getPrefWidth() / 2.0);
        swap.setLayoutY(scene.getHeight() * 0.9 - swap.getPrefHeight() / 2.0);

        back.setLayoutX(5);
        back.setLayoutY(5);
        back.setPrefWidth(60);
        back.setPrefHeight(60);
        back.setText("←");
        back.setFont(Font.font("Arial", FontWeight.BLACK, 24));

        aText.setText("Повернуть влево");
        dText.setText("Повернуть вправо");
        sText.setText("Поменять");
        aText.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        dText.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        sText.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        aText.setLayoutX(left.getLayoutX() + left.getPrefWidth() / 2. - 250);
        aText.setLayoutY(left.getLayoutY() - 10);
        dText.setLayoutX(right.getLayoutX() + right.getPrefWidth() / 2 - 250);
        dText.setLayoutY(right.getLayoutY() - 10);
        sText.setLayoutX(swap.getLayoutX() + swap.getPrefWidth() / 2 - 250);
        sText.setLayoutY(swap.getLayoutY() - 10);

        aText.setWrappingWidth(500);
        aText.setTextAlignment(TextAlignment.CENTER);
        dText.setWrappingWidth(500);
        dText.setTextAlignment(TextAlignment.CENTER);
        sText.setWrappingWidth(500);
        sText.setTextAlignment(TextAlignment.CENTER);

        bigCircle.setCenterX(scene.getWidth() / 2);
        bigCircle.setCenterY(scene.getHeight() / 2);
        bigCircle.setRadius(240);
        bigCircle.setFill(Color.web("#2B2B2B", 1.0));
        bigCircle.setStrokeWidth(3);
        bigCircle.setStroke(Color.BLACK);

        smallCircle.setCenterX(scene.getWidth() / 2);
        smallCircle.setCenterY(scene.getHeight() / 2);
        smallCircle.setRadius(130);
        smallCircle.setFill(Color.web("#252525", 1.0));
        smallCircle.setStrokeWidth(3);
        smallCircle.setStroke(Color.BLACK);

        this.triangle.add(new CubicCurve());
        this.triangle.add(new CubicCurve());

        this.triangle.get(0).setStartX(getOnCirclePosX(bigCircle.getCenterX(), 185, 2 * (Math.PI / 4)));
        this.triangle.get(0).setStartY((double) getOnCirclePosY(bigCircle.getCenterY(), 185, 1 * (Math.PI / 4)) - 40);

        this.triangle.get(0).setControlX1((double) getOnCirclePosX(bigCircle.getCenterX(), 230, 1 * (Math.PI / 4)) + 70);
        this.triangle.get(0).setControlY1((double) getOnCirclePosY(bigCircle.getCenterY(), 230, 1 * (Math.PI / 4)) - 70);

        this.triangle.get(0).setControlX2((double) getOnCirclePosX(bigCircle.getCenterX(), 230, 1 * (Math.PI / 4)) + 70);
        this.triangle.get(0).setControlY2((double) getOnCirclePosY(bigCircle.getCenterY(), 230, 1 * (Math.PI / 4)) - 20);

        this.triangle.get(0).setEndX(getOnCirclePosX(bigCircle.getCenterX(), 185, 2 * (Math.PI / 4)));
        this.triangle.get(0).setEndY((double) getOnCirclePosY(bigCircle.getCenterY(), 185, 2 * (Math.PI / 4)) + 40);

        this.triangle.get(1).setStartX(getOnCirclePosX(bigCircle.getCenterX(), 185, 2 * (Math.PI / 4)));
        this.triangle.get(1).setStartY((double) getOnCirclePosY(bigCircle.getCenterY(), 185, 3 * (Math.PI / 4)) - 40);

        this.triangle.get(1).setControlX1((double) getOnCirclePosX(bigCircle.getCenterX(), 230, 3 * (Math.PI / 4)) - 70);
        this.triangle.get(1).setControlY1((double) getOnCirclePosY(bigCircle.getCenterY(), 230, 3 * (Math.PI / 4)) - 70);

        this.triangle.get(1).setControlX2((double) getOnCirclePosX(bigCircle.getCenterX(), 230, 3 * (Math.PI / 4)) - 70);
        this.triangle.get(1).setControlY2((double) getOnCirclePosY(bigCircle.getCenterY(), 230, 3 * (Math.PI / 4)) - 20);

        this.triangle.get(1).setEndX(getOnCirclePosX(bigCircle.getCenterX(), 185, 2 * (Math.PI / 4)));
        this.triangle.get(1).setEndY((double) getOnCirclePosY(bigCircle.getCenterY(), 185, 2 * (Math.PI / 4)) + 40);

        this.triangle.get(0).setFill(Color.web("#464435", 0.5));
        this.triangle.get(1).setFill(Color.web("#464435", 0.5));

        for (int i = 0; i < 8; i++) {
            indicatorsArr.add(new Circle());
            pane.getChildren().add(indicatorsArr.get(i));
            indicatorsArr.get(i).setRadius(30);
            indicatorsArr.get(i).setCenterX(getOnCirclePosX(bigCircle.getCenterX(), 225, i * (Math.PI / 4)));
            indicatorsArr.get(i).setCenterY(getOnCirclePosY(bigCircle.getCenterY(), 225, i * (Math.PI / 4)));
            indicatorsArr.get(i).setStrokeWidth(3);
            indicatorsArr.get(i).setStroke(Color.BLACK);
        }
        this.setIndicatorsArray(indicatorsArr, this);
        pane.getChildren().add(bigCircle);
        pane.getChildren().add(smallCircle);
        pane.getChildren().add(this.triangle.get(0));
        pane.getChildren().add(this.triangle.get(1));
        cirArr = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            cirArr.add(new Circle());
            pane.getChildren().add(cirArr.get(i));
            cirArr.get(i).setRadius(25);
            cirArr.get(i).setCenterX(getOnCirclePosX(bigCircle.getCenterX(), 185, i * (Math.PI / 4)));
            cirArr.get(i).setCenterY(getOnCirclePosY(bigCircle.getCenterY(), 185, i * (Math.PI / 4)));
            cirArr.get(i).setStrokeWidth(3);
            cirArr.get(i).setStroke(Color.web("#7A8052", 0.45));
        }
        triangleRotateDistance = Math.abs(cirArr.get(1).getCenterX()-cirArr.get(3).getCenterX());
        getTriangleRotateStartingPoint = cirArr.get(1).getCenterX();
        setCirclesArray(cirArr, this);
        setActivities(scene, pane, sceneBack, stage);
    }
    private void setActivities(Scene scene, Pane pane, Scene sceneBack, Stage stage){
        pressed.add(false);
        scene.setOnKeyPressed(e -> {
            //Если не нажато, то выполни действие
            if (!pressed.get(0)) {
                if (e.getCode() == KeyCode.A) {
                    this.rotateLock(-2);
                }
                if (e.getCode() == KeyCode.D) {
                    this.rotateLock(2);
                }
                if (e.getCode() == KeyCode.S) {
                    this.rotateTriangle();
                }
                pressed.set(0, true);
                if (this.isCompleted()){
                    fillVictory(pane, scene, stage, sceneBack);
                }
            }
        });
        scene.setOnKeyReleased(e -> {
            //Отпустил - не нажато
            pressed.set(0, false);
        });
        this.left.setOnMouseClicked(mouseEvent -> {
            this.rotateLock(-2);
            if (this.isCompleted()) {
                fillVictory(pane, scene, stage, sceneBack);
            }
        });

        this.right.setOnMouseClicked(mouseEvent -> {
            this.rotateLock(2);
            if (this.isCompleted()) {
                fillVictory(pane, scene, stage, sceneBack);
            }
        });
        this.swap.setOnMouseClicked(mouseEvent -> {
            rotateTriangle();
            if (this.isCompleted()) {
                fillVictory(pane, scene, stage, sceneBack);
            }
        });
        back.setOnMouseClicked(mouseEvent -> stage.setScene(sceneBack));
    }
    private void fillVictory(Pane pane, Scene scene, Stage stage, Scene sceneBack){
        Rectangle layer = new Rectangle();
        layer.setWidth(scene.getWidth());
        layer.setHeight(scene.getHeight());
        layer.setFill(Color.web("#398030",0.4));
        Text victoryText = new Text();
        Button backVictoryButton = new Button("Вернуться к выбору уровней");
        pane.getChildren().add(layer);
        pane.getChildren().add(victoryText);
        pane.getChildren().add(backVictoryButton);
        backVictoryButton.setPrefWidth(400);
        backVictoryButton.setPrefHeight(80);
        backVictoryButton.setLayoutX(scene.getWidth()/2-backVictoryButton.getPrefWidth()/2);
        backVictoryButton.setLayoutY(150);
        backVictoryButton.setFont(Font.font("Arial", FontWeight.BLACK, 24));
        victoryText.setText("Великолепно!\nВы прошли этот уровень!");
        victoryText.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        victoryText.setLayoutY(60);
        victoryText.setWrappingWidth(700);
        victoryText.setLayoutX(scene.getWidth()/2 - 350);
        victoryText.setTextAlignment(TextAlignment.CENTER);
        scene.setOnKeyPressed(e -> {});
        left.setOnMouseClicked(e -> {});
        swap.setOnMouseClicked(e -> {});
        right.setOnMouseClicked(e -> {});
        backVictoryButton.setOnMouseClicked(e -> stage.setScene(sceneBack));
    }

    private Color getCellsColor(int cellsColorIndex) {
        return AppSettings.SINGLETON.getColorMap().get(cellsColorIndex);
    }

    private int getCellsColorIndexes(int index) {
        return this.cellsColorIndexes.get(index);
    }

    private int getPlacesColorIndexes(int index) {
        return this.placesColorIndexes.get(index);
    }

    private static int getOnCirclePosX(double circleX, double l, double angle) {
        return (int) (circleX + l * Math.cos(angle));
    }

    private static int getOnCirclePosY(double circleY, double l, double angle) {
        return (int) (circleY + l * Math.sin(angle));
    }
    private boolean isCompleted() {
        for (int i = 0; i < 8; i++) {
            if (this.getCellsColorIndexes(i) != this.getPlacesColorIndexes(i)) {
                return false;
            }
        }
        return true;
    }
    private void setCirclesArray(ArrayList<Circle> cirArr, Lock lock) {
        for (int i = 0; i < 8; i++) {
            cirArr.get(i).setFill(lock.getCellsColor(lock.getCellsColorIndexes(i)));
            if (lock.getCellsColorIndexes(i) == 0) {
                cirArr.get(i).setRadius(7);
            } else {
                cirArr.get(i).setRadius(25);
            }
        }
    }
    private void setIndicatorsArray(ArrayList<Circle> rectArr, Lock lock) {
        new Thread(() -> {
            for (int j = 0; j <= 22; j++) {
                try {
                    Thread.sleep(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int k = j;
                Platform.runLater(() -> {
                    for (int i = 0; i < 8; i++) {
                        rectArr.get(i).setFill(lock.getCellsColor(lock.getPlacesColorIndexes(i)));
                        if ((lock.getCellsColorIndexes(i) == getPlacesColorIndexes(i) || lock.getPlacesColorIndexes(i) == 0) && rectArr.get(i).getRadius() != 25) {
                            rectArr.get(i).setRadius(47 - k);
                        }
                        if ((lock.getCellsColorIndexes(i) != getPlacesColorIndexes(i) && lock.getPlacesColorIndexes(i) != 0) && rectArr.get(i).getRadius() != 47) {
                            rectArr.get(i).setRadius(25 + k);
                        }
                    }
                });
            }
        }).start();
    }

    private void rotateLock(int a) {
        Collections.rotate(this.cellsColorIndexes, a);
        this.setIndicatorsArray(this.indicatorsArr, this);
        new Thread(() -> {
            for (int j = 0; j <= 40; j++) {
                try {
                    Thread.sleep(3 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int k = j;
                Platform.runLater(() -> {
                    for (int i = 0; i < 8; i++) {
                        double formula = a/2.0*k * (Math.PI / 80)+(i-a)*(Math.PI / 4);
                        cirArr.get(i).setCenterX(getOnCirclePosX(bigCircle.getCenterX(), 185,  formula));
                        cirArr.get(i).setCenterY(getOnCirclePosY(bigCircle.getCenterY(), 185,  formula));
                    }
                    this.setCirclesArray(this.cirArr, this);
                });
            }
        }).start();
    }

    private void rotateTriangle() {
        int a1, b1, c1;
        a1 = this.cellsColorIndexes.get(1);
        b1 = this.cellsColorIndexes.get(2);
        c1 = this.cellsColorIndexes.get(3);
        this.cellsColorIndexes.set(1, b1);
        this.cellsColorIndexes.set(2, c1);
        this.cellsColorIndexes.set(3, a1);
        this.setIndicatorsArray(this.indicatorsArr, this);

        new Thread(() -> {
            for (int j = 0; j <= 20; j++) {
                try {
                    Thread.sleep(6 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int k = j;
                Platform.runLater(() -> {
                    for (int i = 1; i <= 2; i++) {
                        double formula1 = -k * (Math.PI / 80)+(i+1)*(Math.PI / 4);
                        cirArr.get(i).setCenterX(getOnCirclePosX(bigCircle.getCenterX(), 185,  formula1));
                        cirArr.get(i).setCenterY(getOnCirclePosY(bigCircle.getCenterY(), 185,  formula1));
                    }
                    cirArr.get(3).setCenterX(getTriangleRotateStartingPoint-k*triangleRotateDistance/20);
                    this.setCirclesArray(this.cirArr, this);
                });
            }
        }).start();

    }
    private ArrayList<Integer> generateComposition(int emptyCells, int sameCells1, int sameCells2) {
        //Заполняем список нужными комплектами одинаковых и разных
        ArrayList<Integer> palette = new ArrayList<>();
        for (int i = 1; i < AppSettings.SINGLETON.getColorMap().size() - 1; i++) {
            palette.add(i);
        }
        ArrayList<Integer> newCells = new ArrayList<>();
        //Кладем пустые
        for (int i = 0; i < emptyCells; i++)
            newCells.add(0);
        //Кладем одинаковые первый цвет
        int k = rand.nextInt(palette.size());
        for (int i = 0; i < sameCells1; i++) {
            newCells.add(palette.get(k));
        }
        palette.remove(k);
        //Кладем одинаковые второй цвет
        k = rand.nextInt(palette.size());
        for (int i = 0; i < sameCells2; i++)
            newCells.add(palette.get(k));
        palette.remove(k);

        int length = 8 - emptyCells - sameCells1 - sameCells2;

        for (int i = 0; i < length; i++) {
            k = rand.nextInt(palette.size());
            newCells.add(palette.get(k));
            palette.remove(k);
        }
        return newCells;
    }
    private ArrayList<Integer> entanglement(ArrayList<Integer> newCells) {
        //Перемешивание 100 движенй, (случайное число будет соответствовать элементу списка децствий (С) Колосов (Наш))}
        int k;
        for(int i=0;i<100;i++){
            k=rand.nextInt(3);
            if(k==0){
                Collections.rotate(newCells, 2);
            }
            if(k==1){
                Collections.rotate(newCells, -2);
            }
            if(k==2){
                int a1 = newCells.get(1);
                int b1 = newCells.get(2);
                int c1 = newCells.get(3);
                newCells.set(1, b1);
                newCells.set(2, c1);
                newCells.set(3, a1);
            }
        }
        return newCells;
    }
}
