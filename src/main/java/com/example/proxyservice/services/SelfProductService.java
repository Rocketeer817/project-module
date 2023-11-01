package com.example.proxyservice.services;

import com.example.proxyservice.models.Product;
import com.example.proxyservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfProductService implements IProductService {

    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();

    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product createProduct(Product product) {
        Product product1 = productRepository.save(product);
        return product1;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Optional<Product> product1 = productRepository.findById(productId);
        if(product1.isEmpty()){
            System.out.println("No such product");
            return null;
        }
        else{
            product.setId(productId);
            if(product.getDescription() == null){
                product.setDescription(product1.get().getDescription());
            }
            if(product.getPrice()==0){
                product.setPrice(product1.get().getPrice());
            }
            if(product.getCategory() == null){
                product.setCategory(product1.get().getCategory());
            }
            if(product.getTitle() == null){
                product.setTitle(product1.get().getTitle());
            }
            if(product.getImage() == null){
                product.setImage(product1.get().getImage());
            }
            productRepository.save(product);
        }
        return product;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        product.setId(productId);
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            System.out.println("No such product");
            return null;
        }
        else{
            productRepository.deleteById(id);
        }
        return product.get();
    }


    @Override
    public List<Product> getAllProductsSortedById() {
        return productRepository.findAllByOrderByIdDesc();
    }
}
