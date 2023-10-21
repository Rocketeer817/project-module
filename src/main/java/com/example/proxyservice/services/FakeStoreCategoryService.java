package com.example.proxyservice.services;

import com.example.proxyservice.clients.FakeStore.client.FakeStoryCategoryClient;
import com.example.proxyservice.clients.FakeStore.dto.FakeStoreProductDto;
import com.example.proxyservice.models.Categories;
import com.example.proxyservice.models.Product;
import com.example.proxyservice.services.FakeProductStoreService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FakeStoreCategoryService implements ICategoryService{
    private FakeStoryCategoryClient fakeStoryCategoryClient;
    private FakeStoreDtoConversions fakeStoreDtoConversions;

    public FakeStoreCategoryService(FakeStoryCategoryClient fakeStoryCategoryClient, FakeStoreDtoConversions fakeStoreDtoConversions){
        this.fakeStoryCategoryClient = fakeStoryCategoryClient;
        this.fakeStoreDtoConversions = fakeStoreDtoConversions;
    }


    @Override
    public List<String> getCategories() {
        return fakeStoryCategoryClient.getCategories();
    }

    @Override
    public List<Product> getAllProductsOfSingleCategory(String categoryName) {
        return fakeStoryCategoryClient.getAllProductsOfSingleCategory(categoryName).stream().map(fakeStoreProductDto -> fakeStoreDtoConversions.getProduct(fakeStoreProductDto)).toList();
    }

}
