package com.example.proxyservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Categories extends BaseModel{
    private String name;
    private String description;
    private List<Product> productList;
}
