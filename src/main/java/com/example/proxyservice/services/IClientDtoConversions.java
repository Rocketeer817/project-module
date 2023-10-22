package com.example.proxyservice.services;

import com.example.proxyservice.clients.IClientDto;
import com.example.proxyservice.models.Product;
import org.springframework.stereotype.Component;


public interface IClientDtoConversions {
    public Product getProduct(IClientDto clientDto);
    public IClientDto getClientProductDto(Product product);
}
