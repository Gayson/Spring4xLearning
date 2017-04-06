package com.gayson.domain;

/**
 * Created by jixunzhen on 2017/4/5.
 */
public class Car {
    private String brand;
    private String color;
    private int maxSpeed;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Brand: "+brand+", Color: "+color+", MaxSpeed: "+maxSpeed;
    }
}
