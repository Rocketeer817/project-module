package com.example.proxyservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Categories extends BaseModel{
    private String name;
    private String description;
    //1 cat -> many products
    //1 cat <- 1 product
    //1 : M
    @OneToMany(mappedBy = "category")
    @Fetch(FetchMode.SELECT)
    private List<Product> productList;
}
