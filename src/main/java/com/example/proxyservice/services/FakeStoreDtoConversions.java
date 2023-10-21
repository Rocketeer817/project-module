package com.example.proxyservice.services;

import com.example.proxyservice.clients.FakeStore.dto.FakeStoreProductDto;
import com.example.proxyservice.clients.IClientDto;
import com.example.proxyservice.models.Categories;
import com.example.proxyservice.models.Product;
import org.springframework.stereotype.Component;

@Component
public class FakeStoreDtoConversions implements IClientDtoConversions{

    private Product getProduct(FakeStoreProductDto fakeStoreProductDto){
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

    @Override
    public Product getProduct(IClientDto clientDto) {
        if(!(clientDto instanceof FakeStoreProductDto)){
            throw new IllegalArgumentException("ClientDto is not of type FakeStoreProductDto");
        }
        return getProduct((FakeStoreProductDto)clientDto);
    }

    @Override
    public FakeStoreProductDto getClientProductDto(Product product){
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
