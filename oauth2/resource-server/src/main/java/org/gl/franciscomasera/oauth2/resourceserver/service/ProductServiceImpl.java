package org.gl.franciscomasera.oauth2.resourceserver.service;

import org.gl.franciscomasera.oauth2.resourceserver.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getProducts() {
        return List.of(
                new Product(UUID.randomUUID(), "Rice", 1.0),
                new Product(UUID.randomUUID(), "Tuna", 10.0),
                new Product(UUID.randomUUID(), "Red car", 100.0),
                new Product(UUID.randomUUID(), "Black charcoal", 1000.0),
                new Product(UUID.randomUUID(), "Coat", 10000.0)
        );
    }
}
