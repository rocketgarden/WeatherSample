package net.vinceblas.weathersample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather")
    Call<WeatherResponse> getZipcodeWeather(@Query("zip") int zipcode, @Query("appid") String apiKey);

}
