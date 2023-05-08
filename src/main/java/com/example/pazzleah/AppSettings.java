package com.example.pazzleah;
import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.Map;

public class AppSettings {
    public static final AppSettings SINGLETON = new AppSettings();

    AppSettings() {
        colorMap = new HashMap<>();
        colorMap.put(0, Color.web("#2B2B2B",1)); //Пустой
        colorMap.put(1, Color.web("#FFFFFF",1)); //Белый
        colorMap.put(2, Color.web("#DEBA05",1)); //Желтый
        colorMap.put(3, Color.web("#01BAD7",1)); //Голубой
        colorMap.put(4, Color.web("#1F8E00",1)); //Зеленый
        colorMap.put(5, Color.web("#0047AB",1)); //синий
        colorMap.put(6, Color.web("#FF2E96",1)); //Розовый
        colorMap.put(7, Color.web("#F60001",1)); //Красный
        colorMap.put(8, Color.web("#9D9D9D",1)); //Серый
        colorMap.put(9, Color.web("#AC17FE",1)); //Фиолетовый
        colorMap.put(10, Color.web("#854316",1)); //Коричневый
        colorMap.put(11, Color.web("#31D6C8",1)); //Бирюзовый
    }
    private final Map<Integer, Color> colorMap;

    public Map<Integer, Color> getColorMap() {
        return colorMap;
    }
}

