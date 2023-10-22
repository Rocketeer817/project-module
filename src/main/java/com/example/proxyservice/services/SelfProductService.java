package com.example.proxyservice.services;

import com.example.proxyservice.models.Product;
import com.example.proxyservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfProductService implements IProductService {

    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        Product product1 = productRepository.save(product);
        return product1;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

//    @Override
//    public List<Product> getProducts() {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ProductDto[] productDtos = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDto[].class).getBody();
//        List<Product> products = new ArrayList<>();
//        for (ProductDto productDto : productDtos) {
//            products.add(getProduct(productDto));
//        }
//        return products;
//    }
//
//    @Override
//    public Product getProductById(Long id) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{0}", ProductDto.class,id).getBody();
//        System.out.println(productDto);
//        return getProduct(productDto);
//    }
//
//    private Product getProduct(ProductDto productDto) {
//        Product product =  new Product() {
//            {
//                setId(productDto.getId());
//                setTitle(productDto.getTitle());
//                setPrice(productDto.getPrice());
//                setDescription(productDto.getDescription());
//                setImage(productDto.getImage());
//            }
//        };
//
//        Categories category = new Categories();
//        category.setName(productDto.getCategory());
//        product.setCategory(category);
//
//        return product;
//    }
//
//
//    public Product createProduct(Product productDto) {
//        Product product = getProduct(productDto);
//        System.out.println("Product created with details - "+product);
//        return product;
//    }
//
//
//    public Product updateProduct(Long id,Product product) throws IllegalArgumentException {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ProductDto productDto1 = restTemplate.getForEntity("https://fakestoreapi.com/products/{0}", ProductDto.class,id).getBody();
//
//        if(productDto1 == null){
//            System.out.println("Product not found with id - "+id);
//            throw new IllegalArgumentException("Product not found with id - "+id);
//        }
//        else {
//            Product product1 = getProduct(productDto1);
//            System.out.println("Product replaced with details - "+product1 );
//            return product1;
//        }
//
//    }
//
//    public Product replaceProduct(Long id, Product product) throws IllegalArgumentException  {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ProductDto productDto1 = restTemplate.getForEntity("https://fakestoreapi.com/products/{0}", ProductDto.class,id).getBody();
//
//        if(productDto1 == null){
//            System.out.println("Product not found with id - "+id);
//            throw new IllegalArgumentException("Product not found with id - "+id);
//        }
//        else {
//            Product product1 = getProduct(productDto1);
//            restTemplate.put("https://fakestoreapi.com/products/{0}", , id);
//            System.out.println("Product replaced with details - "+product1 );
//            return product1;
//        }
//
//    }
//
//
//    public Product deleteProduct(Long id) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{0}", ProductDto.class,id).getBody();
//        if(productDto == null){
//            System.out.println("Product not found with id - "+id);
//            throw new IllegalArgumentException("Product not found with id - "+id);
//        }
//        else {
//            Product product = getProduct(productDto);
//            System.out.println("Product deleted with details - "+product );
//            return product;
//        }
//    }
//
//    private ProductDto getFakeStoreProductDto(Product product) {
//        ProductDto productDto = new ProductDto();
//        productDto.setId(product.getId());
//        productDto.setTitle(product.getTitle());
//        productDto.setPrice(product.getPrice());
//        productDto.setDescription(product.getDescription());
//        productDto.setImage(product.getImage());
//        productDto.setCategory(product.getCategory().getName());
//        return productDto;
//    }
}
