package com.example.andresvelasquez.myapplication.services;

import com.example.andresvelasquez.myapplication.models.City;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Firma de los servicios
 */
public interface WeatherService {

  //http://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=cdfbc99145392451b32ada315bfc1e97
  @GET("weather")
  Call<City> listCities(@Query("q") String city,@Query("appid")String key);
  @GET("weather")
  Call<City> listCitiesCelcius(@Query("q") String city,@Query("appid")String key,@Query("units") String value);

}
