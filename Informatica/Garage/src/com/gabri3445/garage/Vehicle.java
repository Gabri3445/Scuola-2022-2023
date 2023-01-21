package com.gabri3445.garage;

import java.math.BigDecimal;

public class Vehicle {
    private final String name;
    private final String brand;
    private final String licensePlate;
    private final int entranceHour;
    private BigDecimal costToEnter;
    private int hoursPassed;
    public Vehicle(String name, String brand, String licensePlate, int entranceHour) {
        this.name = name;
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.hoursPassed = 1;
        this.entranceHour = entranceHour;
    }

    public int getHoursPassed() {
        return hoursPassed;
    }

    public void setHoursPassed(int hoursPassed) {
        this.hoursPassed = hoursPassed;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getEntranceHour() {
        return entranceHour;
    }

    public BigDecimal getCostToEnter() {
        return costToEnter;
    }

    public void setCostToEnter(BigDecimal costToEnter) {
        this.costToEnter = costToEnter;
    }
}
