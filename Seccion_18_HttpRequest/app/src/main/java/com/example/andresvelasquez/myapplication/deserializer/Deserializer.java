package com.example.andresvelasquez.myapplication.deserializer;

import com.example.andresvelasquez.myapplication.models.City;
import com.example.andresvelasquez.myapplication.models.Temperature;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class Deserializer implements JsonDeserializer<City> {

  @Override
  public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {

      int id = json.getAsJsonObject().get("id").getAsInt();
      String name = json.getAsJsonObject().get("name").getAsString();
      String country = json.getAsJsonObject().get("sys").getAsJsonObject().get("country").getAsString();

      City city = new City(name,id,new Temperature(),country);
      return city;
  }
}
