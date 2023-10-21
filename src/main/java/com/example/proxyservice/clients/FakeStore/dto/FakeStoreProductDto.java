package com.example.proxyservice.clients.FakeStore.dto;

import com.example.proxyservice.clients.IClientDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto implements IClientDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
