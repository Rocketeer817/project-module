package com.example.proxyservice.services;

import com.example.proxyservice.clients.IClientDto;
import com.example.proxyservice.models.Product;

public interface IClientDtoConversions {
    public Product getProduct(IClientDto clientDto);
    public IClientDto getClientProductDto(Product product);
}
