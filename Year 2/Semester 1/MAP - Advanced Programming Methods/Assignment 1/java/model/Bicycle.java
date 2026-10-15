package model;

public class Bicycle implements Vehicle {
    private String color;

    public Bicycle(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return "Bicycle | Color: " + color;
    }
}