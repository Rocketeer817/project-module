package com.example.proxyservice.dtos;

import com.example.proxyservice.models.Categories;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class ProductDto {
    private String title;
    private double price;
    private String description;
    private String brand;
    private Categories category;
    private String imageURL;
}
