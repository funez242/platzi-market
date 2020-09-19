package com.platzi.platzimarket.persistence.mapper;

import com.platzi.platzimarket.domain.Category;
import com.platzi.platzimarket.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    //Alguna vez podriamos requerir el mapeo inverso
    //Esto es muy facil si usamos la siguiente notacion
    @InheritInverseConfiguration// esto hace el mapeo completo de categorias pero recordemos que el atributo productos no lo vamos a mapear
    @Mapping(target = "productos", ignore = true) // con esto omitomos que se mapee productos
    Categoria toCategoria(Category category);
}
