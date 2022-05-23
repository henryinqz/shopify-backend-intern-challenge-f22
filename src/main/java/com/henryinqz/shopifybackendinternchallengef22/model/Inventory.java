package com.henryinqz.shopifybackendinternchallengef22.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("inventory")
public class Inventory {
    @Id
    public String id;

    public String name;
    public int quantity;
    public Location location;

    public Inventory(String name, int quantity, Location location) {
        this.name = name;
        this.quantity = quantity;
        this.location = location;
    }
}
