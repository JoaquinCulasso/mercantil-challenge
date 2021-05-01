package com.ma.product.controller;

import com.ma.product.dto.ProductDTO;
import com.ma.product.entity.ProductEntity;
import com.ma.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductController {

    private final ProductService productService;
    private final Map<String, String> response = new HashMap<>();

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Iterable<ProductEntity>> getProducts() {

        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @GetMapping(value = "{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductById(@PathVariable(name = "idProduct") String idProduct) {

        try {
            Optional<ProductEntity> optionalProduct = productService.findById(idProduct);

            if (!optionalProduct.isPresent()) {
                response.put("error", "Producto no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(optionalProduct.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {

        try {

            if (productService.existsById(productDTO.getId())) {
                response.put("error", "ya existe un producto con el mismo id");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            ProductEntity productEntity = new ProductEntity();

            productEntity.setId(productDTO.getId());
            productEntity.setName(productDTO.getName());
            productEntity.setShort_description(productDTO.getShort_description());
            productEntity.setLong_description(productDTO.getLong_description());
            productEntity.setUnit_price(productDTO.getUnit_price());


            productService.save(productEntity);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "{idProduct}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProductById(@PathVariable(name = "idProduct") String idProduct, @RequestBody ProductDTO productDTO) {

        try {
            Optional<ProductEntity> optionalProduct = productService.findById(idProduct);

            if (!optionalProduct.isPresent()) {
                response.put("error", "Producto no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            ProductEntity updateProduct = new ProductEntity();

            updateProduct.setId(optionalProduct.get().getId());
            updateProduct.setName(optionalProduct.get().getName());
            updateProduct.setShort_description(optionalProduct.get().getShort_description());
            updateProduct.setLong_description(optionalProduct.get().getLong_description());
            updateProduct.setUnit_price((optionalProduct.get().getUnit_price()));

            productService.save(updateProduct);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value = "{idProduct}")
    public ResponseEntity<?> deleteProductById(@PathVariable(name = "idProduct") String idProduct) {

        try {
            if (!productService.existsById(idProduct)) {
                response.put("error", "Producto no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            productService.deleteById(idProduct);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
