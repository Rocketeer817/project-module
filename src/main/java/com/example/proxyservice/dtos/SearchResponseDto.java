package com.example.proxyservice.dtos;

import com.example.proxyservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SearchResponseDto {
    private List<ProductResponseDto> products;
    private int totalPages;
    private int totalProducts;

    public static SearchResponseDto from(Page<Product> productPage){
        SearchResponseDto searchResponseDto = new SearchResponseDto();
        searchResponseDto.setProducts(productPage.getContent().stream().map(product -> ProductResponseDto.from(product)).collect(Collectors.toList()));
        searchResponseDto.totalPages = productPage.getTotalPages();
        searchResponseDto.totalProducts = (int) productPage.getTotalElements();

        return searchResponseDto;
    }
}
