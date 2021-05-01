package com.ma.product.service;

import com.ma.product.entity.ProductEntity;
import com.ma.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductEntity> findById(String idProduct) {
        return productRepository.findById(idProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(String idProduct) {
        return productRepository.existsById(idProduct);
    }

    @Override
    @Transactional
    public void save(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }

    @Override
    @Transactional
    public void deleteById(String idProduct) {
        productRepository.deleteById(idProduct);
    }
}
