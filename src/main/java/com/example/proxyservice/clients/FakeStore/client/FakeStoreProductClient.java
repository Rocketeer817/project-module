package com.example.proxyservice.clients.FakeStore.client;

import com.example.proxyservice.clients.FakeStore.dto.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDto> getAllProducts(){
        FakeStoreProductDto[] productDtos = restTemplateBuilder.build().getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        return Arrays.asList(productDtos);

    }

    public List<FakeStoreProductDto> getAllProductsSortedById(){

        FakeStoreProductDto[] productDtos = restTemplateBuilder.build().getForObject("https://fakestoreapi.com/products?sort=desc", FakeStoreProductDto[].class);
        return Arrays.asList(productDtos);
    }

    public FakeStoreProductDto getSingleProduct(long id){
        return restTemplateBuilder.build().getForEntity("https://fakestoreapi.com/products/{0}", FakeStoreProductDto.class,id).getBody();
    }

    public FakeStoreProductDto createProduct(FakeStoreProductDto productDto){
        return restTemplateBuilder.build().postForEntity("https://fakestoreapi.com/products", productDto, FakeStoreProductDto.class).getBody();
    }

    public void replaceProduct(long id, FakeStoreProductDto productDto) throws RestClientException {
        restTemplateBuilder.build().put("https://fakestoreapi.com/products/{0}", productDto, id);
    }

    public FakeStoreProductDto updateProduct(long id, FakeStoreProductDto productDto) throws RestClientException {
        return requestForEntity(HttpMethod.PATCH, "https://fakestoreapi.com/products/{0}", productDto, FakeStoreProductDto.class, id).getBody();
        //return restTemplateBuilder.build().patchForObject("https://fakestoreapi.com/products/{0}", productDto, FakeStoreProductDto.class, id);
    }

    public FakeStoreProductDto deleteProduct(long id) throws RestClientException{
        FakeStoreProductDto productDto = getSingleProduct(id);
        restTemplateBuilder.build().delete("https://fakestoreapi.com/products/{0}",id);
        return productDto;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
