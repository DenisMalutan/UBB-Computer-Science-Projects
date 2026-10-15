package model;

public class Car implements Vehicle {
    private String color;

    public Car(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return "Car | Color: " + color;
    }
}