package org.gl.franciscomasera.oauth2.resourceserver.service;

import org.gl.franciscomasera.oauth2.resourceserver.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
}
