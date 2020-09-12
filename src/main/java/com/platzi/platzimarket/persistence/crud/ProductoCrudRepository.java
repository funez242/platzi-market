package com.platzi.platzimarket.persistence.crud;

import com.platzi.platzimarket.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

// Esta interfaz nos va a permitir hacer operaciones CRUD de nustros productos
// Esta debe extender de CrudRepository y tener dentro de pico parentesis
// el Nombre de la clase de producto y el tipo de dato de su clave primaria o Id
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
}
