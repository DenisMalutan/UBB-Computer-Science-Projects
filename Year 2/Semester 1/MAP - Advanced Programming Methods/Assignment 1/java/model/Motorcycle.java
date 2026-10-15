package model;

public class Motorcycle implements Vehicle {
    private String color;

    public Motorcycle(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return "Motorcycle | Color: " + color;
    }
}