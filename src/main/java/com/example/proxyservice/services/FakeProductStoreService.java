package com.example.proxyservice.services;

import com.example.proxyservice.clients.FakeStore.client.FakeStoreClient;
import com.example.proxyservice.clients.FakeStore.dto.FakeStoreProductDto;
import com.example.proxyservice.models.Categories;
import com.example.proxyservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeProductStoreService implements IProductService{
    FakeStoreClient fakeStoreClient;
    public FakeProductStoreService(FakeStoreClient fakeStoreClient){
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public List<Product> getProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos =  fakeStoreClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(getProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) {
       FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.getSingleProduct(id);
       return getProduct(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = getFakeStoreProductDto(product);
        FakeStoreProductDto fakeStoreProductDto1 = fakeStoreClient.createProduct(fakeStoreProductDto);
        return getProduct(fakeStoreProductDto1);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto = getFakeStoreProductDto(product);
        try{
            fakeStoreClient.replaceProduct(productId,fakeStoreProductDto);
        }
        catch (RestClientException exception){
            throw new IllegalArgumentException("Product with id: " + productId + " does not exist");
        }
        return getProduct(fakeStoreProductDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto = getFakeStoreProductDto(product);
        FakeStoreProductDto fakeStoreProductDto1 = fakeStoreClient.updateProduct(productId,fakeStoreProductDto);
        return getProduct(fakeStoreProductDto1);
    }

    @Override
    public Product deleteProduct(Long id) {
        try{
            return getProduct(fakeStoreClient.deleteProduct(id));
        }
        catch (RestClientException exception){
            throw new IllegalArgumentException("Product with id: " + id + " does not exist");
        }
    }

    public Product getProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product(){
            {
                setId(fakeStoreProductDto.getId());
                setTitle(fakeStoreProductDto.getTitle());
                setPrice(fakeStoreProductDto.getPrice());
                setDescription(fakeStoreProductDto.getDescription());
                setImage(fakeStoreProductDto.getImage());
            }
        };

        Categories category = new Categories();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    public FakeStoreProductDto getFakeStoreProductDto(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto(){
            {
                setId(product.getId());
                setTitle(product.getTitle());
                setPrice(product.getPrice());
                setDescription(product.getDescription());
                setImage(product.getImage());
            }
        };

        fakeStoreProductDto.setCategory(product.getCategory().getName());
        return fakeStoreProductDto;
    }


}
