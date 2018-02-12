package com.example.andresvelasquez.practica_seccion_6.Model;

/**
 * Created by andresvelasquez on 11/02/18.
 */

public class Fruit {
    public String name;
    public int quantity;
    public String description;
    public int image;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public static final int LIMIT_QUANTITY = 10;
    public static final int RESET_VALUE_QUANTITY = 0;
    public Fruit(String name,int image,int quantity,String description){
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
    }

    public Fruit() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int maxCuantity) {
        maxCuantity = maxCuantity;
    }

    public void addQuantity(int quantity){
        if(this.quantity < LIMIT_QUANTITY){
            this.quantity += quantity;
        }
    }
    public void resetQuantity(){
        this.quantity = RESET_VALUE_QUANTITY;
    }
}
