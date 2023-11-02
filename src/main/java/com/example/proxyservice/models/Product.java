package com.example.proxyservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private String brand;
    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.LAZY)
    private Categories category;
    private String image;
    private int quantity;
    private double rating;
}
