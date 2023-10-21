package com.example.proxyservice.controllers;

import com.example.proxyservice.dtos.ProductRequestDto;
import com.example.proxyservice.dtos.ProductResponseDto;
import com.example.proxyservice.models.Categories;
import com.example.proxyservice.models.Product;
import com.example.proxyservice.services.FakeProductStoreService;
import com.example.proxyservice.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    public ProductController(FakeProductStoreService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
         return new ResponseEntity<>(productService.getProducts().stream().map(product -> getProductDto(product)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto){
        return new ResponseEntity<>(getProductDto(productService.createProduct(getProduct(productRequestDto))), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getSingleProduct(@PathVariable("productId") Long productId)
    {

        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(getProductDto(product), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> replaceProduct(@PathVariable("productId") Long productId, @RequestBody ProductRequestDto productRequestDto){
        Product product = productService.updateProduct(productId,getProduct(productRequestDto));
        return new ResponseEntity<>(getProductDto(product), HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductRequestDto productRequestDto){
        try{
            Product product = productService.updateProduct(productId,getProduct(productRequestDto));
            return new ResponseEntity<>(getProductDto(product), HttpStatus.OK);
        }
        catch(IllegalArgumentException e){
            throw e;
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable("productId") Long productId){
        try{
            Product product = productService.deleteProduct(productId);
            return new ResponseEntity<>(getProductDto(product), HttpStatus.OK);
        }
        catch(IllegalArgumentException exception){
            System.out.println(exception.toString());
            throw exception;
        }
    }

    private Product getProduct(ProductRequestDto productRequestDto) {
        Product product =  new Product() {
            {
                setTitle(productRequestDto.getTitle());
                setPrice(productRequestDto.getPrice());
                setDescription(productRequestDto.getDescription());
                setImage(productRequestDto.getImage());
            }
        };

        Categories category = new Categories();
        category.setName(productRequestDto.getCategory());
        product.setCategory(category);

        return product;
    }

    private ProductResponseDto getProductDto(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto(){
            {
                setId(product.getId());
                setTitle(product.getTitle());
                setPrice(product.getPrice());
                setDescription(product.getDescription());
                setImage(product.getImage());
                setCategory(product.getCategory().getName());
            }
        };
        return productResponseDto;
    }

    @ExceptionHandler({IllegalArgumentException.class,Exception.class})
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<>("kuch gadbad hogaya", HttpStatus.NOT_FOUND);
    }

}
