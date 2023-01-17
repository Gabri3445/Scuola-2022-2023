package com.gabri3445.mcdonald;

import java.math.BigDecimal;

public class Burger {
    /**
     * ID of the burger
     */
    private final int id;
    /**
     * Description of the burger
     */
    private final String description;
    /**
     * Price in euro of the burger
     */
    private final BigDecimal price;
    /**
     * Quantity of the burger available
     */
    private int quantity;
    /**
     * Quantity purchased by the client
     */
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
