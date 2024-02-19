package com.example.proxyservice.services;

import com.example.proxyservice.dtos.SortParam;
import com.example.proxyservice.models.Product;
import com.example.proxyservice.repository.ProductRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> searchProducts(String query, int pageNo, int pageSize, List<SortParam> sortParamList){
        return productRepository.findByTitleEquals(query, PageRequest.of(pageNo,pageSize));
    }

}
