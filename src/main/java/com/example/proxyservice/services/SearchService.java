package com.example.proxyservice.services;

import com.example.proxyservice.dtos.SortParam;
import com.example.proxyservice.models.Product;
import com.example.proxyservice.repository.ProductRepository;
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
        Sort sort;
        //if there are no elements in sortParamList
        if(sortParamList.size() == 0){
            return productRepository.findByTitleEquals(query, PageRequest.of(pageNo,pageSize));
        }
        //for first item in the sortList
        if(sortParamList.get(0).getSortType().equals("ASC")){
            sort = Sort.by(sortParamList.get(0).getFieldName()).ascending();
        }
        else{
            sort = Sort.by(sortParamList.get(0).getFieldName()).descending();
        }

        for(int i=1;i<sortParamList.size();i++){
            SortParam sortParam = sortParamList.get(i);
            if(sortParam.getSortType().equals("ASC")){
                sort.and(Sort.by(sortParam.getFieldName()).ascending());
            }
            else if(sortParam.getSortType().equals("DESC")){
                sort.and(Sort.by(sortParam.getFieldName()).descending());
            }
        }
        return productRepository.findByTitleContaining(query, PageRequest.of(pageNo,pageSize,sort));
    }

}
