package com.henryinqz.shopifybackendinternchallengef22.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("inventory")
public class Inventory {
    @Id
    public String id;

    public String name;
    public int quantity;

    public Inventory(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
