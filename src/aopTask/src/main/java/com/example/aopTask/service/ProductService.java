package com.example.aopTask.service;

import com.example.aopTask.entity.Product;
import com.example.aopTask.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        Integer productId = product.getId();
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product specified is not valid"));
        Integer newQuantity = product.getQuantity();
        existingProduct.setQuantity(newQuantity);
        productRepository.save(existingProduct);

        return existingProduct;
    }
}
