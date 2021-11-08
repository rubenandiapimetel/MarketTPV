package com.ruben.market.domain.repository;

import java.util.List;
import java.util.Optional;

import com.ruben.market.domain.Product;

//esta interface solo sirve para declarar desde el dominio todas
//las funciones que vaya a querer usar

public interface ProductRepository {

    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScaseProduct(int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);
}
