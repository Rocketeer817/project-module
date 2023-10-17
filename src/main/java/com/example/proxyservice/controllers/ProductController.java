package com.example.proxyservice.controllers;

import com.example.proxyservice.dtos.ProductDto;
import com.example.proxyservice.models.Product;
import com.example.proxyservice.services.IProductService;
import com.example.proxyservice.services.ProductService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId){

        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto){
        Product product = productService.updateProduct(productId,productDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto){
        try{
            Product product = productService.updateProduct(productId,productDto);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Long productId){
        try{
            Product product = productService.deleteProduct(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        catch(IllegalArgumentException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
