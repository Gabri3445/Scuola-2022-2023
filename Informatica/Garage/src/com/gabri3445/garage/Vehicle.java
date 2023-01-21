package com.gabri3445.garage;

import java.math.BigDecimal;
import java.util.Date;

public class Vehicle {
    private final String name;
    private final String brand;
    private final String licensePlate;
    private final Date entranceDate;
    private BigDecimal costToEnter;

    public Vehicle(String name, String brand, String licensePlate, Date entranceDate) {
        this.name = name;
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.entranceDate = entranceDate;
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

    public Date getEntranceDate() {
        return entranceDate;
    }

    public BigDecimal getCostToEnter() {
        return costToEnter;
    }

    public void setCostToEnter(BigDecimal costToEnter) {
        this.costToEnter = costToEnter;
    }
}
