package com.platzi.platzimarket.persistence.mapper;

import com.platzi.platzimarket.domain.Product;
import com.platzi.platzimarket.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})//En este caso como haremos uso de categoria y esta tiene su propio mapper como id lo agregamos con uses
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category")

    })
    Product toProduct(Producto producto);
    List<Product> toProducts(List<Producto> productos);// Ya tenemos el mapper singular y ahora haremos el plural y no tenemos que volver a definir el mappeo, lo hace internamente


    //Ahora haremos la conversion contraria
    @InheritInverseConfiguration//hace el mapeo inverso pero debemos omitir el codigo de barras que no esta considerado en product
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);


}
