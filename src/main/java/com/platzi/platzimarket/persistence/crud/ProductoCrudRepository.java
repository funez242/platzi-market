package com.platzi.platzimarket.persistence.crud;

import com.platzi.platzimarket.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// Esta interfaz nos va a permitir hacer operaciones CRUD de nustros productos
// Esta debe extender de CrudRepository y tener dentro de pico parentesis
// el Nombre de la clase de producto y el tipo de dato de su clave primaria o Id
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    // El siguiente metodo hace uso de Query Methods que son una forma mas rapida y flexible de traernos datos de la base de datos
    // Simplemente debe cumplic con cierta sintaxis como llevar el findBy seguido del nombre del atributo por el que queremos buscar
    // escrito en camelCase y podriamos agregar mas atributos como orderBy... quedando -> findByIdCategoriaOrderByNombreAsc(int idCategoria)
    List<Producto> findByIdCategoria(int idCategoria);

    // Igual se podria Usar la anotacion @Query de la siguiente manera
    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?",nativeQuery = true);
    List<Producto> getByCategoria(int idCategoria);//En este caso podemos nombrar el metodo como querramos

    // Los query methods tambien soportan los operadores opcionales de la nuevas versiones de Java
    // no olvifrmod que los parametros se deben llamar como estan en nuestra clase
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}
