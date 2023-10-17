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
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
