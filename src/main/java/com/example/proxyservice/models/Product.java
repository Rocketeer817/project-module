package com.example.proxyservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@ToString
@Entity
//@Document(indexName = "productsearchservice", type = "product")
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private String brand;
    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    //@Field(type = FieldType.Nested, includeInParent = true)
    private Categories category;
    private String image;
    private int quantity;
}
