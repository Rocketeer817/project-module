package com.example.proxyservice.dtos;

import com.example.proxyservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;


@Getter
@Setter
@ToString
public class CategoryDto {
    private String name;
    private String description;
    private List<Product> productList;
}
