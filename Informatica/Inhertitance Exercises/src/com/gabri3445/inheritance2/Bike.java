package com.gabri3445.inheritance2;

public class Bike extends Vehicle {
    private final int displacement;

    public Bike(String licensePlate, String maker, String model, boolean isBroken, int displacement) {
        super(licensePlate, maker, model, isBroken);
        this.displacement = displacement;
    }

    public int getDisplacement() {
        return displacement;
    }
}
