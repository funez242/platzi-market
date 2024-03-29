package com.platzi.platzimarket.persistence.mapper;

import com.platzi.platzimarket.domain.PurchaseItem;
import com.platzi.platzimarket.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {
    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            //@Mapping(source = "total", target = "total")//Como se llaman igual no es necesario ponerlo
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping( target = "compra",ignore = true),
            @Mapping( target = "producto",ignore = true),
            @Mapping( target = "id.idCompra",ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem item);
}
