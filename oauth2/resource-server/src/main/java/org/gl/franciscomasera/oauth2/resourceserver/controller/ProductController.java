package org.gl.franciscomasera.oauth2.resourceserver.controller;

import lombok.AllArgsConstructor;
import org.gl.franciscomasera.oauth2.resourceserver.entity.Product;
import org.gl.franciscomasera.oauth2.resourceserver.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

}
