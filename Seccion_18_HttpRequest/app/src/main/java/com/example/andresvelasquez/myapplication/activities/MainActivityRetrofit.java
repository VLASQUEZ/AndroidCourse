package com.example.andresvelasquez.myapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.example.andresvelasquez.myapplication.R;
import com.example.andresvelasquez.myapplication.models.City;
import com.example.andresvelasquez.myapplication.services.API;
import com.example.andresvelasquez.myapplication.services.WeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityRetrofit extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_retrofit);

    /**
     * Service instance
     */
    WeatherService service = API.getAPI().create(WeatherService.class);

    /**
     * Call instance
     */
    Call<City> cityCall = service.listCitiesCelcius("Bogota,CO", API.API_KEY, "metric");

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
