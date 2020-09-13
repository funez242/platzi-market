package com.platzi.platzimarket.domain.repository;

import com.platzi.platzimarket.domain.Product;

import java.util.List;
import java.util.Optional;

// Con esto se establecen las reglas que va a tener nuestro dominio al momento que cualquier repositorio quiera acceder a productos
// dentro de una base de datos, esto nos va a garantizar no acoplar nuestra solucion a una base de datos especifica sino que siempre estemos
// habalano en terminos de dominio, en esta caso prouctos
// Los objetos de dominio son muy importantes dentro de nuestra aplicacion por que son los encargaos de llevar toda la logica de nuestro negocio
public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);
}
