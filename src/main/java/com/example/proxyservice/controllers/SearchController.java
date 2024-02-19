package com.example.proxyservice.controllers;

import com.example.proxyservice.dtos.ProductResponseDto;
import com.example.proxyservice.dtos.SearchRequestDto;
import com.example.proxyservice.dtos.SearchResponseDto;
import com.example.proxyservice.models.Product;
import com.example.proxyservice.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping
    public SearchResponseDto search(@RequestBody SearchRequestDto searchRequestDto){
//        return SearchResponseDto.from(searchService
//                .searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageNo(), searchRequestDto.getPageSize(), searchRequestDto.getSortParamList())
//                        .stream().map(product -> ProductResponseDto.from(product)).collect(Collectors.toList()));

        Page<Product> productPage= searchService.searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageNo(), searchRequestDto.getPageSize(), searchRequestDto.getSortParamList());
        return SearchResponseDto.from(productPage);
    }
}
