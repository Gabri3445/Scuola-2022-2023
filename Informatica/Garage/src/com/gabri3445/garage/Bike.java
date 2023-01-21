package com.gabri3445.garage;

import java.math.BigDecimal;
import java.util.Date;

public class Bike extends Vehicle {
    public Bike(String name, String brand, String licensePlate, Date entranceDate) {
        super(name, brand, licensePlate, entranceDate);
        setCostToEnter(BigDecimal.valueOf(1));
    }
}
