package com.gabri3445.inheritance2;

public class Vehicle {
    private final String licensePlate;
    private final String maker;
    private final String model;
    private boolean isBroken;
    public Vehicle(String licensePlate, String maker, String model, boolean isBroken) {
        this.licensePlate = licensePlate;
        this.maker = maker;
        this.model = model;
        this.isBroken = isBroken;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getMaker() {
        return maker;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    public String getModel() {
        return model;
    }

    public boolean isBroken() {
        return isBroken;
    }
}
