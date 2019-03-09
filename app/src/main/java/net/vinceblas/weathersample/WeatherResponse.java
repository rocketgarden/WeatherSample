package net.vinceblas.weathersample;

import java.util.ArrayList;

public class WeatherResponse {

    class Weather {

        int id;
        String main;
        String description;

    }

    ArrayList<Weather> weather;

}
