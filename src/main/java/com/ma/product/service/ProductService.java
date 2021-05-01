package com.ma.product.service;

import com.ma.product.dto.ProductDTO;
import com.ma.product.entity.ProductEntity;

import java.util.Optional;

public interface ProductService {

    Iterable<ProductEntity> findAll();

    Optional<ProductEntity> findById(String idProduct);

    boolean existsById(String idProduct);

    void save(ProductEntity productEntity);

    void deleteById(String idProduct);
}
