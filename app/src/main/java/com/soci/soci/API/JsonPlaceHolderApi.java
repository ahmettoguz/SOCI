package com.soci.soci.API;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.soci.soci.Model.Weather;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface JsonPlaceHolderApi {
    String WEATHER_URL = "https://api.weather.com/v3/wx/conditions/historical/dailysummary/";

    @GET("30day?apiKey=e1f10a1e78da46f5b10a1e78da96f525&icaoCode=LTAC&units=e&language=EN&format=json")
    Call<Weather> getWeather(); // To send findBook as a query string

    //Call<JsonElement> getBooks(@Query("findBook") String findBooks); // To send findBook as a query string

}
