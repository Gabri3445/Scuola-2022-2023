package com.gabri3445.garage;

import java.util.Date;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Garage garage = new Garage();
        garage.enterVehicle(new Car("giglo", "giglo", "giglo", new Date()));
    }
}
