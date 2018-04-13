package com.example.andresvelasquez.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class Temperature {

  private float temp;
  private float pressure;
  private float humidity;
  @SerializedName("temp_min")
  private float tempMin;
  @SerializedName("temp_max")
  private float tempMax;

  public Temperature() {
  }

  public Temperature(float temp, float pressure, float humidity, float tempMin, float tempMax) {
    this.temp = temp;
    this.pressure = pressure;
    this.humidity = humidity;

    this.tempMin = tempMin;
    this.tempMax = tempMax;
  }

  public float getTemp() {
    return temp;
  }

  public void setTemp(float temp) {
    this.temp = temp;
  }

  public float getPression() {
    return pressure;
  }

  public void setPression(float pressure) {
    this.pressure = pressure;
  }

  public float getHumidity() {
    return humidity;
  }

  public void setHumidity(float humidity) {
    this.humidity = humidity;
  }

  public float getTempMin() {
    return tempMin;
  }

  public void setTempMin(float tempMin) {
    this.tempMin = tempMin;
  }

  public float getTempMax() {
    return tempMax;
  }

  public void setTempMax(float tempMax) {
    this.tempMax = tempMax;
  }
}
