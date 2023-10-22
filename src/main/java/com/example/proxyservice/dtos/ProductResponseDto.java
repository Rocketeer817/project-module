package com.example.proxyservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
public class ProductResponseDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
