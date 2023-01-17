package com.gabri3445.mcdonald;

import java.math.BigDecimal;

public class Burger {
    private final int id;
    private final String description;
    private int quantity;
    private final BigDecimal price;
    private int boughtQuantity;

    public Burger(int id, String description, int quantity, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getBoughtQuantity() {
        return boughtQuantity;
    }

    public void setBoughtQuantity(int boughtQuantity) {
        this.boughtQuantity = boughtQuantity;
    }
}
