package com.gabri3445.inheritance2;

public class Car extends Vehicle {
    private final String type;

    public Car(String licensePlate, String maker, String model, boolean isBroken, String type) {
        super(licensePlate, maker, model, isBroken);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
