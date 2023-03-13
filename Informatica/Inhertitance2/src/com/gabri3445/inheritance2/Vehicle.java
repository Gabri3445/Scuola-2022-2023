package com.gabri3445.inheritance2;

public class Vehicle {
    private final String licensePlate;

    public Vehicle(String licensePlate, String maker, String model, boolean isBroken) {
        this.licensePlate = licensePlate;
        this.maker = maker;
        this.model = model;
        this.isBroken = isBroken;
    }

    private final String maker;
    private final String model;
    private final boolean isBroken;

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public boolean isBroken() {
        return isBroken;
    }
}
