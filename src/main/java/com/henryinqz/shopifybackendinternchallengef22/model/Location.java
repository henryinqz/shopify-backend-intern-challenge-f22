package com.henryinqz.shopifybackendinternchallengef22.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("location")
public class Location {
    @Id
    public String id;

    public String name;
    public String address;
    public String zipCode;
    public String city;
    public String state;
    public String country;

    public Location(String name, String address, String zipCode, String city, String state, String country) {
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.country= country;
    }
}
