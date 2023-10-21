package com.example.proxyservice.clients.FakeStore.client;

import com.example.proxyservice.clients.FakeStore.dto.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoryCategoryClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoryCategoryClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }


    public List<String> getCategories(){
        String url = "https://fakestoreapi.com/products/categories";
        return restTemplateBuilder.build().getForObject(url, List.class);
    }

    public List<FakeStoreProductDto> getAllProductsOfSingleCategory(String categoryName){
        String url = "https://fakestoreapi.com/products/category/{0}";
        return Arrays.stream(restTemplateBuilder.build().getForObject(url, FakeStoreProductDto[].class, categoryName)).toList();
    }


}
