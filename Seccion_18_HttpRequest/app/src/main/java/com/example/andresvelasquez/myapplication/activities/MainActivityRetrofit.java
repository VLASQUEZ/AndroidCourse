package com.example.andresvelasquez.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.andresvelasquez.myapplication.R;
import com.example.andresvelasquez.myapplication.models.City;
import com.example.andresvelasquez.myapplication.services.WeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityRetrofit extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_retrofit);
    /**
     * Retrofit instance
     */
    Retrofit retrofit = new Retrofit.Builder()
                  .baseUrl("http://samples.openweathermap.org/data/2.5/")
                  .addConverterFactory(GsonConverterFactory.create())
                  .build();

    /**
     * Service instance
     */
    WeatherService service = retrofit.create(WeatherService.class);

    /**
     * Call instance
     */
    Call<City> cityCall = service.listCities("Bogota,CO","cdfbc99145392451b32ada315bfc1e97");

    cityCall.enqueue(new Callback<City>() {
      @Override public void onResponse(Call<City> call, Response<City> response) {
        City city = response.body();
      }

      @Override public void onFailure(Call<City> call, Throwable t) {
        Toast.makeText(MainActivityRetrofit.this,t.getMessage(),Toast.LENGTH_SHORT).show();
      }
    });
  }
}
