package com.example.proxyservice.services;

import com.example.proxyservice.clients.FakeStore.client.FakeStoreProductClient;
import com.example.proxyservice.clients.FakeStore.dto.FakeStoreProductDto;
import com.example.proxyservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeProductStoreService implements IProductService{
    FakeStoreProductClient fakeStoreProductClient;
    FakeStoreDtoConversions fakeStoreDtoConversions;
    public FakeProductStoreService(FakeStoreProductClient fakeStoreProductClient, FakeStoreDtoConversions fakeStoreDtoConversions){
        this.fakeStoreProductClient = fakeStoreProductClient;
        this.fakeStoreDtoConversions = fakeStoreDtoConversions;
    }

    @Override
    public List<Product> getProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos =  fakeStoreProductClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(fakeStoreDtoConversions.getProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) {
       FakeStoreProductDto fakeStoreProductDto = fakeStoreProductClient.getSingleProduct(id);
       return fakeStoreDtoConversions.getProduct(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreDtoConversions.getClientProductDto(product);
        FakeStoreProductDto fakeStoreProductDto1 = fakeStoreProductClient.createProduct(fakeStoreProductDto);
        return fakeStoreDtoConversions.getProduct(fakeStoreProductDto1);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreDtoConversions.getClientProductDto(product);
        try{
            fakeStoreProductClient.replaceProduct(productId,fakeStoreProductDto);
        }
        catch (RestClientException exception){
            throw new IllegalArgumentException("Product with id: " + productId + " does not exist");
        }
        return fakeStoreDtoConversions.getProduct(fakeStoreProductDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreDtoConversions.getClientProductDto(product);
        FakeStoreProductDto fakeStoreProductDto1 = fakeStoreProductClient.updateProduct(productId,fakeStoreProductDto);
        return fakeStoreDtoConversions.getProduct(fakeStoreProductDto1);
    }

    @Override
    public Product deleteProduct(Long id) {
        try{
            return fakeStoreDtoConversions.getProduct(fakeStoreProductClient.deleteProduct(id));
        }
        catch (RestClientException exception){
            throw new IllegalArgumentException("Product with id: " + id + " does not exist");
        }
    }


}
