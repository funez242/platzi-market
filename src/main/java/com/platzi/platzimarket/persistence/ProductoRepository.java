package com.platzi.platzimarket.persistence;

import com.platzi.platzimarket.domain.Product;
import com.platzi.platzimarket.domain.repository.ProductRepository;
import com.platzi.platzimarket.persistence.crud.ProductoCrudRepository;
import com.platzi.platzimarket.persistence.entity.Producto;
import com.platzi.platzimarket.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//como esta clase va a interactuar con la base de datos se le pone la anotacion de repository
@Repository
public class ProductoRepository implements ProductRepository {// Orientamos este repositorio al dominio haciendo que implemente ProductRspository
    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){//hacemos una conversion de producto a product
         List<Producto> productos= (List<Producto>) productoCrudRepository.findAll();
         return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoria(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        // como no tenemos un mapper que retorne un optional list hacemos un map de productos
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return Optional.empty();
    }

    @Override
    public Product save(Product product) {
        return null;
    }


    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    public void delete(int idProducto){
         productoCrudRepository.deleteById(idProducto);
    }

}
