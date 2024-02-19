package com.example.proxyservice.controllers;

import com.example.proxyservice.dtos.ProductRequestDto;
import com.example.proxyservice.dtos.ProductResponseDto;
import com.example.proxyservice.exceptions.InvalidAuthorizationException;
import com.example.proxyservice.models.Categories;
import com.example.proxyservice.models.Product;
import com.example.proxyservice.security.ITokenValidator;
import com.example.proxyservice.security.JwtClaim;
import com.example.proxyservice.security.TokenValidator;
import com.example.proxyservice.services.IProductService;
import com.example.proxyservice.services.SelfProductService;
import jakarta.annotation.Nullable;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;
    private ITokenValidator tokenValidator;

    public ProductController(IProductService selfProductService, ITokenValidator tokenValidator){

        this.productService = selfProductService;
        this.tokenValidator = tokenValidator;
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
    //@Nullable @RequestHeader("Authorization") String authToken
    public ResponseEntity<ProductResponseDto> getSingleProduct(@PathVariable("productId") Long productId) throws InvalidAuthorizationException
    {
        //JwtClaim jwtClaim = validateToken(authToken);
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

    private Product getProduct(ProductRequestDto productRequestDto)
    {
        //Initializing like this is not creating a Product object for some reason.
        // It is creating a ProductController$1 class seemingly an anonymous object.
//        Product product =  new Product() {
//            {
//                setTitle(productRequestDto.getTitle());
//                setPrice(productRequestDto.getPrice());
//                setDescription(productRequestDto.getDescription());
//                setImage(productRequestDto.getImage());
//            }
//        };

        //Use this for converting one dto to entity
        Product product = new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        product.setDescription(productRequestDto.getDescription());
        product.setImage(productRequestDto.getImage());
        Categories category = new Categories();
        category.setName(productRequestDto.getCategory());
        product.setCategory(category);

        return product;
    }

    private ProductResponseDto getProductDto(Product product){
        ProductResponseDto productResponseDto1 = new ProductResponseDto();
        productResponseDto1.setId(product.getId());
        productResponseDto1.setTitle(product.getTitle());
        productResponseDto1.setPrice(product.getPrice());
        productResponseDto1.setDescription(product.getDescription());
        productResponseDto1.setImage(product.getImage());
        productResponseDto1.setCategory(product.getCategory().getName());
        return productResponseDto1;
    }

    @ExceptionHandler({IllegalArgumentException.class,Exception.class})
    public ResponseEntity<String> handleException(Exception e){
        System.out.println(e);
        return new ResponseEntity<>("kuch gadbad hogaya", HttpStatus.NOT_FOUND);
    }

    private JwtClaim validateToken(String token,long userID) throws InvalidAuthorizationException
    {
        Optional<JwtClaim> jwtClaim = tokenValidator.validateToken(token,userID);
        if(jwtClaim.isEmpty()){
            throw new InvalidAuthorizationException("Invalid token");
        }
        return jwtClaim.get();
    }

}
