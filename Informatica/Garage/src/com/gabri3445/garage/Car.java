package com.gabri3445.garage;

import java.math.BigDecimal;
import java.util.Date;

public class Car extends Vehicle{
    public Car(String name, String brand, String licensePlate, Date entranceDate) {
        super(name, brand, licensePlate, entranceDate);
        setCostToEnter(BigDecimal.valueOf(2));
    }
}
