package com.ma.product.repository;

import com.ma.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findById(String idProduct);

    boolean existsById(String idProduct);

    ProductEntity save(ProductEntity productEntity);

    void deleteById(String idProduct);
}
