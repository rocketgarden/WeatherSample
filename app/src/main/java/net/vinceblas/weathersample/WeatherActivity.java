package net.vinceblas.weathersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    public static final String EXTRA_ZIP = "WeatherActivity.ZIP";

    public static final String API_OPENWEATHERMAP_ORG = "https://api.openweathermap.org/";
    public static final String API_KEY = "dbf6f32a52322c228c6f3fff2d4d4fe5";
    private WeatherApi weatherApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_results);

        final int zip = getIntent().getIntExtra(EXTRA_ZIP, 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_OPENWEATHERMAP_ORG)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = retrofit.create(WeatherApi.class);

        doApiCall(zip);

    }

    private void doApiCall(int zipcode) {
        weatherApi.getZipcodeWeather(zipcode, API_KEY).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Toast.makeText(WeatherActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                showWeather(response.body().weather.get(0).main, response.body().weather.get(0).description);
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(WeatherActivity.this, "Failure!", Toast.LENGTH_SHORT).show();

                Log.e("VB_WEATHER", "Something broke", t);

            }
        });
    }

    private void showWeather(String mainWeather, String weatherDescription) {
        TextView mainText = findViewById(R.id.text_weather_main);
        TextView descriptionText = findViewById(R.id.text_weather_description);

        mainText.setText(mainWeather);
        descriptionText.setText(weatherDescription);

    }
}
