package com.example.proxyservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private String brand;
    private Categories category;
    private String image;
}
